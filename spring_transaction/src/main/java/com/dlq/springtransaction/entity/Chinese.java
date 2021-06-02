package com.dlq.springtransaction.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-06-02 21:04
 */
@Data
public class Chinese {
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    private String name;
    private Integer age;
}
