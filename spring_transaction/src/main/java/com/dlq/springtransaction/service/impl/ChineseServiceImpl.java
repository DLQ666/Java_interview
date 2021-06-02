package com.dlq.springtransaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dlq.springtransaction.entity.Chinese;
import com.dlq.springtransaction.mapper.ChineseMapper;
import com.dlq.springtransaction.service.ChineseService;
import org.springframework.stereotype.Service;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-06-02 21:10
 */
@Service
public class ChineseServiceImpl extends ServiceImpl<ChineseMapper, Chinese> implements ChineseService {
}
