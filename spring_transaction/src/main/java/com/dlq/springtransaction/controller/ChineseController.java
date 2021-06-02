package com.dlq.springtransaction.controller;

import com.dlq.springtransaction.entity.Chinese;
import com.dlq.springtransaction.entity.Usa;
import com.dlq.springtransaction.service.ChineseService;
import com.dlq.springtransaction.service.UsaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-06-02 21:16
 */
@RestController
public class ChineseController {

    @Autowired
    private ChineseService chineseService;
    @Autowired
    private UsaService usaService;

    @GetMapping("/test_aaa")
    public List<Chinese> getAll() {
        List<Chinese> list = chineseService.list();
        return list;
    }

    @GetMapping("/getusa")
    public List<Usa> getAllUsa() {
        List<Usa> list = usaService.list();
        return list;
    }

    @GetMapping("test_transactional")
    public void test() {
        usaService.saveUsa();
    }
}
