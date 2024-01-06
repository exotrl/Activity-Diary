package com.example.audiobook_backend.service;

import com.example.audiobook_backend.pojo.User;

/**
 * @author cly<br />
 * @date: 2023/12/13 19:00<br/>
 * @description: <br/>
 */

public interface UserService {
    public User checkUser(String email, String password);
}
