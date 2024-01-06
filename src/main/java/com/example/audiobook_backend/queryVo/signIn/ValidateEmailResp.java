package com.example.audiobook_backend.queryVo.signIn;

import com.example.audiobook_backend.pojo.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cly<br />
 * @date: 2023/12/25 13:38<br/>
 * @description: <br/>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ValidateEmailResp {
    private Status status;
}
