package com.dlq.springtransaction;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement(proxyTargetClass = true) //事务处理
//@EnableAspectJAutoProxy(exposeProxy = true)
@MapperScan("com.dlq.springtransaction.mapper")
@SpringBootApplication
public class SpringTransactionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringTransactionApplication.class, args);
    }

}
