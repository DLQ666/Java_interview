package com.dlq.springtransaction.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dlq.springtransaction.entity.Usa;
import com.dlq.springtransaction.mapper.UsaMapper;
import com.dlq.springtransaction.service.UsaService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-06-02 21:11
 */
@Service
public class UsaServiceImpl extends ServiceImpl<UsaMapper, Usa> implements UsaService {

    @Autowired
    private UsaServiceImpl usaService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveUsa() {
        Usa usa1 = baseMapper.selectById(2);
        usa1.setUname("tom123");
        baseMapper.updateById(usa1);
        usaService.updateusa();

        int a = 10 / 0;
    }

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void updateusa() {
        Usa usa1 = baseMapper.selectById(1);
        usa1.setUname("jack123");
        baseMapper.updateById(usa1);
    }
}
