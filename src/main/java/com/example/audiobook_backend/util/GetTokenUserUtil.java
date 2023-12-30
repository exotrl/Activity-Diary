package com.example.audiobook_backend.util;

import com.example.audiobook_backend.pojo.User;
import com.example.audiobook_backend.service.impl.utils.UserDetailsImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

/**
 * @author molimark<br />
 * @date: 2023/3/2 23:37<br/>
 * @description: <br/>
 */

@Component
public class GetTokenUserUtil {
    public static User getTokenUser(){
        UsernamePasswordAuthenticationToken authentication =
                (UsernamePasswordAuthenticationToken) SecurityContextHolder.getContext().getAuthentication();
        UserDetailsImpl loginUser = (UserDetailsImpl) authentication.getPrincipal();

        User user = loginUser.getUser();
        return user;
    }
}
