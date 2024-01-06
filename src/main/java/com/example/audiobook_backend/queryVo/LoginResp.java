package com.example.audiobook_backend.queryVo;

import com.example.audiobook_backend.pojo.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cly<br />
 * @date: 2023/12/17 23:08<br/>
 * @description: <br/>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResp {
    private Status status;
    private String user_id;
    private String user_avatar;
    private String user_name;
    private String access_token;
    private Long access_expire;

    public static LoginResp create(Integer code,String msg,String user_id,String user_avatar,String user_name,String access_token,Long access_expire){
        LoginResp loginResp = new LoginResp();
        Status status = new Status(code,msg);
        loginResp.setStatus(status);
        loginResp.setUser_id(user_id);
        loginResp.setUser_avatar(user_avatar);
        loginResp.setUser_name(user_name);
        loginResp.setAccess_token(access_token);
        loginResp.setAccess_expire(access_expire);
        return loginResp;
    }

    public static LoginResp create(Integer code,String msg){
        LoginResp loginResp = new LoginResp();
        Status status = new Status(code,msg);
        loginResp.setStatus(status);
        return loginResp;
    }
}
