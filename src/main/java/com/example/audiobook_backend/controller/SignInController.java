package com.example.audiobook_backend.controller;

import com.example.audiobook_backend.pojo.User;
import com.example.audiobook_backend.queryVo.LoginResp;
import com.example.audiobook_backend.queryVo.signIn.ResetPassResp;
import com.example.audiobook_backend.queryVo.signIn.SendVerificationResp;
import com.example.audiobook_backend.queryVo.signIn.SignInResp;
import com.example.audiobook_backend.queryVo.signIn.ValidateEmailResp;
import com.example.audiobook_backend.service.SignInService;
import com.example.audiobook_backend.util.GetTokenUserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author cly<br />
 * @date: 2023/12/17 23:06<br/>
 * @description: <br/>
 */

@RestController
@RequestMapping("/api/login")
public class SignInController {
    @Autowired
    private SignInService signInService;

    @PostMapping("/login")
    public LoginResp login(@RequestBody Map<String,String> map){
        String email = map.get("email");
        String password = map.get("password");
        System.out.println("email: " + email);
        System.out.println("password: " + password);
        return signInService.login(email,password);
    }

    @GetMapping("/send_verification")
    public SendVerificationResp sendVerification(@RequestParam Map<String,String> map){
        String email = map.get("email");
        System.out.println("调用sendVerification");
        return signInService.sendVerification(email);
    }

    @PostMapping("/validate_email")
    public ValidateEmailResp validateEmail(@RequestParam Map<String,String> map){
        String email = map.get("email");
        String verification = map.get("verification");
        System.out.println("调用validateEmail");
        return signInService.validateEmail(email,verification);
    }

    @PostMapping("/reset_pass")
    public ResetPassResp resetPassword(@RequestParam Map<String,String> map){
        User user = GetTokenUserUtil.getTokenUser();
        String user_id = user.getUserId().toString();
        String email = map.get("email");
        String password = map.get("password");
        System.out.println("调用resetPassword");
        return signInService.resetPassword(user_id,email,password);
    }

    @PostMapping("/signin")
    public SignInResp signIn(@RequestBody Map<String,String> map){
        String username = map.get("username");
        String email = map.get("email");
        String password = map.get("password");
        String verification = map.get("verification");
        System.out.println("调用signIn");
        return signInService.signIn(username,email,password,verification);
    }
}
