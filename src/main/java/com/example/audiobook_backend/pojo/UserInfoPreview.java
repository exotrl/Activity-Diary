package com.example.audiobook_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author molimark<br />
 * @date: 2023/4/5 13:49<br/>
 * @description: <br/>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoPreview {
    private String user_id;
    private String user_name;
    private String user_avatar;
    private String avatar;
    private String user_ip;
    private Boolean is_followed;
}
