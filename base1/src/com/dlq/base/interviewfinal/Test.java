package com.dlq.base.interviewfinal;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-06-03 15:13
 */
public class Test {
    public static void main(String[] args) {
        System.setProperty("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        Test test = new Test();
        test.test(11);
        OutClass outClass = new OutClass();
        outClass.outPrint(22);
    }

    //局部final变量 a，b
    public void test(int b) { //jdk 8及之后的版本：可以省略final的声明
        int a = 10; //jdk 8及之后的版本：可以省略final的声明
        //匿名内部类
        new Thread() {
            public void run() {
                System.out.println(a);
                System.out.println(b);
            }
        }.start();
    }
}

class OutClass {
    private int age = 12;

    public void outPrint(int x) { //jdk 8及之后的版本：可以省略final的声明
        class InClass {
            public void InPrint() {
                System.out.println(x);
                System.out.println(age);
            }
        }
        new InClass().InPrint();
    }
}
