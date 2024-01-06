package com.example.audiobook_backend.service;

import com.example.audiobook_backend.queryVo.LoginResp;
import com.example.audiobook_backend.queryVo.signIn.ResetPassResp;
import com.example.audiobook_backend.queryVo.signIn.SendVerificationResp;
import com.example.audiobook_backend.queryVo.signIn.SignInResp;
import com.example.audiobook_backend.queryVo.signIn.ValidateEmailResp;

/**
 * @author cly<br />
 * @date: 2023/12/6 19:19<br/>
 * @description: <br/>
 */

public interface SignInService {
    LoginResp login(String email, String password);
    SendVerificationResp sendVerification(String email);
    SignInResp signIn(String username,String email, String password, String verification);
    ValidateEmailResp validateEmail(String email, String verification);
    ResetPassResp resetPassword(String userId, String email, String password);
}
