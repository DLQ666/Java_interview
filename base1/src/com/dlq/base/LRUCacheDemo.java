package com.dlq.base;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-05-13 16:36
 */
public class LRUCacheDemo<k, v> extends LinkedHashMap<k, v> {

    private int capacity;//缓存坑位

    /**
     * accessOrder    the ordering mode -
     * <tt>true</tt>  for access-order, 访问顺序
     * <tt>false</tt> for insertion-order 插入顺序
     * @param capacity
     */
    public LRUCacheDemo(int capacity) {
        super(capacity, 0.75F, true);
        this.capacity = capacity;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<k, v> eldest) {
        return super.size() > capacity;
    }

    public static void main(String[] args) {
        LRUCacheDemo lruCacheDemo = new LRUCacheDemo(3);
        lruCacheDemo.put(1,"a");
        lruCacheDemo.put(2,"b");
        lruCacheDemo.put(3,"c");
        System.out.println(lruCacheDemo.keySet());

        lruCacheDemo.put(4,"d");
        System.out.println(lruCacheDemo.keySet());
        System.out.println("--------------------");
        lruCacheDemo.put(3,"c");
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(3,"c");
        System.out.println(lruCacheDemo.keySet());
        lruCacheDemo.put(3,"c");
        System.out.println(lruCacheDemo.keySet());
        System.out.println("--------------------");
        lruCacheDemo.put(5,"e");
        System.out.println(lruCacheDemo.keySet());
    }
}
/**
 * true
 [1, 2, 3]
 [2, 3, 4]
 ----------
 [2, 4, 3]
 [2, 4, 3]
 [2, 4, 3]
 ----------
 [4, 3, 5]
 */

/**
 * false
 [1, 2, 3]
 [2, 3, 4]
 ----------
 [2, 3, 4]
 [2, 3, 4]
 [2, 3, 4]
 ----------
 [3, 4, 5]
 */
