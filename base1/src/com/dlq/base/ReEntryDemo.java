package com.dlq.base;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-06-09 20:32
 */
public class ReEntryDemo {

    static Object objectLockA = new Object();
    Lock lock = new ReentrantLock();

    public void m1(){
        synchronized (objectLockA){
            System.out.println("----外层");
            synchronized (objectLockA){
                System.out.println("---中层");
                synchronized (objectLockA){
                    System.out.println("---内层");
                }
            }
        }
    }

    public void m2() {
        lock.lock();
        try {
            System.out.println("---外层");
            lock.lock();
            try {
                System.out.println("---内层");
            } finally {
                lock.unlock();  //如果注释掉这层解锁  多线程操作 就会发生死锁
            }
        } finally {
            lock.unlock();   //如果注释掉这层解锁  多线程操作 就会发生死锁
        }
    }

    public static void main(String[] args) {

        ReEntryDemo reEntryDemo = new ReEntryDemo();
        /*new Thread(() -> {
            reEntryDemo.m1();
        }, "a").start();*/
        new Thread(() -> {
            reEntryDemo.m2();
        }, "a").start();

        new Thread(() -> {
            reEntryDemo.m2();
        }, "b").start();
    }
}
