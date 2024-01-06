package com.example.audiobook_backend.service.impl;
import com.example.audiobook_backend.mapper.UserMapper;
import com.example.audiobook_backend.pojo.User;
import com.example.audiobook_backend.service.impl.utils.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author cly<br />
 * @date: 2023/12/16 9:40<br/>
 * @description: <br/>
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userMapper.selectUserByEmail(email);
        if(user == null){
            //用户不存在会显示在登录页面，如果用户名不存在的话，spring-security自带的
            System.out.println("用户不存在");
            throw new RuntimeException("用户不存在");
        }
        System.out.println(user.getUserId());
        return new UserDetailsImpl(user);
    }
}
