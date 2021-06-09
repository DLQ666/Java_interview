package com.dlq.base;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Ticket{

    private int number = 30;
    Lock lock = new ReentrantLock(); //默认就是非公平

    public void sale(){
        lock.lock();
        try {
            if (number > 0){
                System.out.println(Thread.currentThread().getName() + "\t 卖出第：" + (number--) + "\t还剩下：" + number);
            }
        } finally {
            lock.unlock();
        }
    }
}
/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-06-09 20:50
 */
public class SaleTicketDemo {

    public static void main(String[] args) {
        Ticket ticket = new Ticket();
        /*new Thread(() -> {for (int i = 1; i <= 10; i++) ticket.sale();}, "a").start();
        new Thread(() -> {for (int i = 1; i <= 10; i++) ticket.sale();}, "b").start();
        new Thread(() -> {for (int i = 1; i <= 10; i++) ticket.sale();}, "c").start();*/

        //暂停几秒钟线程
        try { TimeUnit.SECONDS.sleep(1); } catch (InterruptedException e) { e.printStackTrace(); }

        /* 线程池 */
        ExecutorService threadPool = Executors.newFixedThreadPool(3);
        try{
            for (int i = 0; i < 32; i++) {
                threadPool.execute(ticket::sale);
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            threadPool.shutdown();
        }
    }
}
