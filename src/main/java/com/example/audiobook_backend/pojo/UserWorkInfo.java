package com.example.audiobook_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author molimark<br />
 * @date: 2023/4/7 13:56<br/>
 * @description: <br/>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserWorkInfo {
    private String user_id;
    private String user_name;
    private String avatar;
    private String introduction; // 用户的个人介绍
    //private String introduction; // 用户的个人介绍
    private String like_num; // 用户收到的点赞总数
    private String comment_num; // 用户收到的评论总数
    private String save_num; // 用户收到的收藏总数
    private Boolean is_followed; // 是否关注了这个用户
}
