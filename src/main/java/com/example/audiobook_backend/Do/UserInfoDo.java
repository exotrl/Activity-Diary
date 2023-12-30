package com.example.audiobook_backend.Do;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoDo {
    private Integer id;
    private String avatar;
    private String create_time;
    private String nickname;
    private String password;
    private Integer type;
    private String update_time;
    private String email;
    private String major;
    private String place;
    private String hobbies;
    private String allowed_view_range_type;
    private String allowed_view_range;
    private Boolean only_followers_allowed;
    private Boolean block_strangers;
    private Boolean remind_private_message;
    private Boolean remind_like_collect;
    private Boolean remind_comment;
    private Boolean remind_befocused;
    private Integer age;
    private String introduction;
}
