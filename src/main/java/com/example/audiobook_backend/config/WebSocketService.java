package com.example.audiobook_backend.config;


import com.alibaba.fastjson.JSON;
import com.example.audiobook_backend.Do.MessageData;
import com.example.audiobook_backend.util.WebSocketMapUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.util.List;


@Component
@ServerEndpoint(value = "/websocket/{userId}")
public class WebSocketService {

    private static final Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    private Session session;

    /*
     *
     * 用户唯一id
     * */
    private String userId;

    /*
     *
     * 在线人数计数
     * */
    private static int count;

    /*
     *
     * userID和Session绑定
     * 给客户端发送消息需要session
     * */

    /*
     *
     * 有连接时回调
     * */
    @OnOpen
    public void onOpen(Session session, @PathParam("userId") String userId) {
        this.session = session;
        this.userId = userId;
        WebSocketMapUtil.put(userId, session);
        count++;
        sendOnLine(userId, "in");
    }

    /*
     *
     * 断开连接时回调
     * */
    @OnClose
    public void onClose(@PathParam("userId") String userId) {

        /*
         *
         * 移除关闭的连接
         * */
        WebSocketMapUtil.remove(userId);
        count--;
        sendOnLine(userId, "out");
    }

    /*
     *
     * 发生错误时回调
     * */
    @OnError
    public void onError(@PathParam("userId") String userId, Throwable error) {
        logger.info(userId + "发生连接错误" + error.getMessage());
        error.printStackTrace();
    }

    /*
     *
     * 收到消息时回调
     * */
    @OnMessage
    public void onMessage(String message, @PathParam("userId") String userId) throws Exception {

        logger.info("收到来自" + userId + "的消息：" + message);

        // 将传送过来的JSON格式数据转换成Object
        ObjectMapper objectMapper = new ObjectMapper();
        MessageData messageData = objectMapper.readValue(message, MessageData.class);

        // 私聊
        if (messageData.getMsgType() == 1) {

            // 发送者的Session
            Session fromSession = WebSocketMapUtil.get(userId);
            // 接收者的Session
            Session toSession = WebSocketMapUtil.get(messageData.getToUserId());
            // 判断接收者是否在线
            if (toSession != null) {
                // 传达消息
                sendMessage(messageData, toSession);
                sendMessage(messageData, fromSession);
            } else {
                MessageData messageData_fail = new MessageData();
                messageData_fail.setFromUserId("系统提示");
                messageData_fail.setMsgData("私聊消息发送失败，对方不在线");
                sendMessage(messageData_fail, fromSession);
            }
        }
        // 群聊
        else {
            // 遍历当前所有在线人
            sendMessageAll(messageData);
            logger.info("这是一条群发消息：" + JSON.toJSONString(messageData));
        }
    }

    /*
     *
     * 发送消息
     * */
    public void sendMessage(MessageData messageData, Session session) {
        session.getAsyncRemote().sendText(JSON.toJSONString(messageData));
    }

    /*
     *
     * 群发消息
     * */
    public void sendMessageAll(MessageData messageData) {
        for (Object object : WebSocketMapUtil.getAllValues()) {
            logger.info("session" + object);
            Session session_map = (Session) object;
            session_map.getAsyncRemote().sendText(JSON.toJSONString(messageData));
        }
    }

    /*
     *
     * 向用户发送在线信息
     * sendType:上线还是下线
     * */
    public void sendOnLine(String userId, String sendType) {
        MessageData messageData = new MessageData();
        messageData.setFromUserId("系统消息");
        StringBuffer stringBuffer = new StringBuffer();
        List<String> userIdList = WebSocketMapUtil.getAllKey();
        for (int i = 0; i < userIdList.size(); i++) {
            stringBuffer.append(userIdList.get(i)).append(";");
        }
        if (sendType.equals("in")) {
            messageData.setMsgData(userId + "加入聊天室, 当前在线用户" + count + "人, 分别是" + stringBuffer.toString());
            logger.info(userId + "加入聊天室");
        }else if(sendType.equals("out")){
            messageData.setMsgData(userId + "退出聊天室, 当前在线用户" + count + "人, 分别是" + stringBuffer.toString());
            logger.info(userId + "退出聊天室");
        }
        sendMessageAll(messageData);
    }
}
