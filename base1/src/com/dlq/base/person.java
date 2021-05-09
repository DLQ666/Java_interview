package com.dlq.base;

import java.util.concurrent.ConcurrentHashMap;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-05-09 20:18
 */
public class person {

    //该name有自己的命名规则，明显不能由外部直接赋值
    //父类类型 变量名 = new 子类对象;
    private String name;
    public void setName(String name){
        this.name = "dlq_"+name;
    }

    public static void main(String[] args) {
        Object object = new String("1");
        System.out.println(object.equals("1"));
    }



}
