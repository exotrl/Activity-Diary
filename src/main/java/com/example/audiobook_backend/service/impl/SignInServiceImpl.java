package com.example.audiobook_backend.service.impl;

import com.example.audiobook_backend.mapper.SignMapper;
import com.example.audiobook_backend.mapper.UserMapper;
import com.example.audiobook_backend.pojo.StatusCode;
import com.example.audiobook_backend.pojo.User;
import com.example.audiobook_backend.queryVo.LoginResp;
import com.example.audiobook_backend.queryVo.signIn.ResetPassResp;
import com.example.audiobook_backend.queryVo.signIn.SendVerificationResp;
import com.example.audiobook_backend.queryVo.signIn.SignInResp;
import com.example.audiobook_backend.queryVo.signIn.ValidateEmailResp;
import com.example.audiobook_backend.service.SignInService;
import com.example.audiobook_backend.service.impl.utils.UserDetailsImpl;
import com.example.audiobook_backend.util.CreateVerificationCodeUtil;
import com.example.audiobook_backend.util.JwtUtil;
import com.example.audiobook_backend.pojo.Status;
import io.jsonwebtoken.Claims;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author molimark<br />
 * @date: 2023/3/6 19:20<br/>
 * @description: <br/>
 */

@Service
public class SignInServiceImpl implements SignInService {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private SignMapper signMapper;

    @Autowired
    private UserMapper userMapper;

    @Override
    public LoginResp login(String email, String password) {
        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email,password);

        // 如果登录失败，会自动处理
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        UserDetailsImpl loginUser = (UserDetailsImpl) authenticate.getPrincipal();
        User userByEmail = userMapper.selectUserByEmail(email);
        System.out.println(userByEmail);
        User user = loginUser.getUser();
        user.setUsername(userByEmail.getUsername());
        System.out.println(user);

        //根据用户名密码进行验证，成功的话生成jwt返回给前端，后续前端的访问会携带该jwt-token
        String jwt = JwtUtil.createJWT(user.getUserId().toString());
        System.out.println("user: " + user.getUserId() + " " + user.getPassword() + " " + "jwt: " +jwt);
        Long accessExpire;
        //获取过期时间,初始过期时间在JwtUtil中设置
        try {
            Claims claims = JwtUtil.parseJWT(jwt);
            Date expiration = claims.getExpiration();
            //getTime返回的是自某个时间起的毫秒数
            accessExpire = expiration.getTime()-System.currentTimeMillis();
        }catch (Exception e){
            throw new RuntimeException(e);
        }
        return LoginResp.create(StatusCode.OK,"登录成功",user.getUserId().toString(),user.getAvatar(),user.getUsername(),jwt,accessExpire);
    }

    @Override
    public SendVerificationResp sendVerification(String email) {
        SendVerificationResp resp = new SendVerificationResp();
        List<String> emails = userMapper.selectEmails(email);
        if(emails != null && emails.size() != 0){
            resp.setStatus(new Status(500,"该邮箱已被注册过"));
            return resp;
        }
        try {
            String authCode = CreateVerificationCodeUtil.generateCode();
            SimpleEmail mail = new SimpleEmail();
            mail.setHostName("smtp.qq.com");//发送邮件的服务器
            //服务器上要加这行，因为服务器的默认发邮件的25端口被封杀了
            mail.setSmtpPort(587);
            mail.setAuthentication("807717165@qq.com","vbeblkmgosclbeji");//刚刚记录的授权码，是开启SMTP的密码
            mail.setFrom("807717165@qq.com","永言（always speaking）");  //发送邮件的邮箱和发件人
            //服务器上加这行会报错
            //mail.setSSLOnConnect(true); //使用安全链接
            mail.addTo(email);//接收的邮箱
            mail.setSubject("永言（always speaking）--用户注册验证User registration verification");//设置邮件的主题
            mail.setMsg("亲爱的用户：\n    您好！感谢您注册永言（always speaking），" +
                    "请您使用验证码：" +
                    authCode +
                    "进行验证，有效期三分钟。\n    " +
                    "期待永言（always speaking）能为您带来美好的使用体验！\n" +
                    "Dear user,\nHello! Thank you for registering for Yong Yan(Always Speaking)." +
                    " Please use the verification code:" + authCode + "  for verification, " +
                    "which is valid for three minutes.\nLooking forward to Yong Yan(Always Speaking)" +
                    " bringing you a wonderful user experience!");//设置邮件的内容
            mail.send();//发送
            List<Integer> ids = signMapper.getIdByEmail(email);
            if(ids==null||ids.size()==0){
                signMapper.addVerification(email,authCode,new Date().getTime());
            }else{
                signMapper.updateVerification(email,authCode,new Date().getTime());
            }
        } catch (EmailException e) {
            e.printStackTrace();
            resp.setStatus(new Status(500,"发送验证码失败"));
        }
        resp.setStatus(new Status(200,"success"));
        return resp;
    }

    @Override
    public SignInResp signIn(String username,String email, String password, String verification) {
        String verification_code = signMapper.getVerificationByEmail(email);
        Long create_time = signMapper.getCreateTimeByEmail(email);
        Long current_time = new Date().getTime();
        System.out.println(current_time-create_time);
        SignInResp resp = new SignInResp();
        if(verification_code==null||!verification_code.equals(verification)
            ||create_time==null||current_time-create_time>180000){
            resp.setStatus(new Status(500,"注册失败"));
            return resp;
        }

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        password = passwordEncoder.encode(password);
        signMapper.addUser(username,email, password);
        resp.setStatus(new Status(200,"success"));
        return resp;
    }

    @Override
    public ValidateEmailResp validateEmail(String email, String verification) {
        String verification_code = signMapper.getVerificationByEmail(email);
        ValidateEmailResp resp = new ValidateEmailResp();
        if(verification_code==null||"".equals(verification_code)||!verification_code.equals(verification)){
            resp.setStatus(new Status(500,"验证码错误"));
        }else{
            resp.setStatus(new Status(200,"success"));
        }
        return resp;
    }

    @Override
    public ResetPassResp resetPassword(String userId, String email, String password) {
        ResetPassResp resp = new ResetPassResp();
        if(userId==null||"".equals(userId)){
            resp.setStatus(new Status(500,"重置密码失败，用户未登录"));
            return resp;
        }
        String user_email = signMapper.getEmailByUserId(userId);
        if(user_email==null||"".equals(user_email)||!user_email.equals(email)){
            resp.setStatus(new Status(500,"邮箱错误"));
            return resp;
        }
        signMapper.updateUserPassword(userId,password);
        resp.setStatus(new Status(200,"success"));
        return resp;
    }
}
