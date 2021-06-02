package com.dlq.springtransaction.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-06-02 21:06
 */
@Data
public class Usa {
    @TableId(value = "uid", type = IdType.AUTO)
    private Integer uid;
    private String uname;
    private Integer uage;
}
