package com.dlq.springtransaction.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dlq.springtransaction.entity.Usa;

/**
 *@description:
 *@author: Hasee
 *@create: 2021-06-02 21:10
 */
public interface UsaService extends IService<Usa> {

    void saveUsa();
}
