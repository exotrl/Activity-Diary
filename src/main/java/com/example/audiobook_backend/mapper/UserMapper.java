package com.example.audiobook_backend.mapper;

import com.example.audiobook_backend.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cly<br />
 * @date: 2023/12/18 0:16<br/>
 * @description: <br/>
 */

@Repository
public interface UserMapper {
    User getUserByEmail(@Param("email") String email, @Param("password") String password);
    User searchUserByUsername(@Param("username") String username);
    User selectById(@Param("user_id") Integer user_id);
    User selectUserByEmail(@Param("email") String email);
    List<String> selectEmails(@Param("email") String email);
}
