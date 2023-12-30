package com.example.audiobook_backend.queryVo;

import com.example.audiobook_backend.pojo.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author molimark<br />
 * @date: 2023/4/8 1:30<br/>
 * @description: <br/>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserInfoResp {
    private Status status;
}
