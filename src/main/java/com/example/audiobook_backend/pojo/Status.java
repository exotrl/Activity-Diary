package com.example.audiobook_backend.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author cly<br />
 * @date: 2023/12/17 23:08<br/>
 * @description: <br/>
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Status {
    private Integer code;
    private String msg;
}
