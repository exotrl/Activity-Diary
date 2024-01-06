package com.example.audiobook_backend.service.impl;

import com.example.audiobook_backend.mapper.UserMapper;
import com.example.audiobook_backend.pojo.User;
import com.example.audiobook_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author cly<br />
 * @date: 2023/12/13 19:00<br/>
 * @description: <br/>
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Override
    public User checkUser(String email, String password) {
        return userMapper.getUserByEmail(email,password);
    }
}
