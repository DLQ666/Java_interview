package com.dlq.study.jvm;

import org.openjdk.jol.info.ClassLayout;
import org.openjdk.jol.vm.VM;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-06-19 17:40
 */
public class JVMDemo {
    public static void main(String[] args) {
        //VM的细节详细情况
        System.out.println(VM.current().details());
        //所有的对象分配的字节都是8的整数倍
        System.out.println(VM.current().objectAlignment());
    }
}
