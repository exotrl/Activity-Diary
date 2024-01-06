package com.example.audiobook_backend.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author cly<br />
 * @date: 2023/12/26 13:41<br/>
 * @description: <br/>
 */

@Repository
public interface SignMapper {
    void addVerification(@Param("email") String email,@Param("verification_code") String verification_code,@Param("create_time") Long create_time);
    List<Integer> getIdByEmail(@Param("email") String email);
    void updateVerification(@Param("email") String email,@Param("verification_code") String verification_code,@Param("create_time") Long create_time);
    String getVerificationByEmail(@Param("email") String email);
    String getEmailByUserId(@Param("user_id") String user_id);
    void updateUserPassword(@Param("user_id") String user_id,@Param("password") String password);
    Long getCreateTimeByEmail(@Param("email") String email);
    void addUser(@Param("username")String username,@Param("email") String email,@Param("password") String password);
}
