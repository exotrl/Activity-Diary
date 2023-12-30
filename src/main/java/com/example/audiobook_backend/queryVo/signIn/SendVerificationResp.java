package com.example.audiobook_backend.queryVo.signIn;

import com.example.audiobook_backend.pojo.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author molimark<br />
 * @date: 2023/7/25 13:38<br/>
 * @description: <br/>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SendVerificationResp {
    private Status status;
}
