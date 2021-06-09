package com.dlq.base;

import java.util.concurrent.TimeUnit;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-06-09 20:01
 */
public class DeadLockDemo {

    static Object objectLockA = new Object();
    static Object objectLockB = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (objectLockA){
                System.out.println(Thread.currentThread().getName() + "\t 自己持有A锁定，期望获得B锁定");
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                synchronized (objectLockB){
                    System.out.println(Thread.currentThread().getName() + "\t 自己持有A锁定，期望获得B锁定");
                }
            }
        }, "a").start();

        new Thread(() -> {
            synchronized (objectLockB) {
                System.out.println(Thread.currentThread().getName() + "\t 自己持有B锁定，期望获得A锁定");
                try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }
                synchronized (objectLockA){
                    System.out.println(Thread.currentThread().getName() + "\t 自己持有B锁定，期望获得A锁定");
                }
            }
        }, "b").start();
    }
}
