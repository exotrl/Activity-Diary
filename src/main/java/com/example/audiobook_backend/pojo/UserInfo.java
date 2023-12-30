package com.example.audiobook_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author molimark<br />
 * @date: 2023/4/7 13:53<br/>
 * @description: <br/>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfo {
    private String avatar;
    private Date create_time;
    private String name;
    private String password;
    private Date update_time;
    private String tot_listen_time;
    private String introduction;
    private String email;
}
