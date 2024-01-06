package com.example.audiobook_backend.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.models.auth.In;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author cly<br />
 * @date: 2023/12/17 23:25<br/>
 * @description: <br/>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @TableId(type = IdType.AUTO)
    private Integer userId;
    private String avatar;
    private Date createTime;
    private String username;
    private String password;
    private Date updateTime;
    private String totListenTime;
    private String introduction;
    private String email;
}
