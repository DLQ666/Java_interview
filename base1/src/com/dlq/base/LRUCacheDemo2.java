package com.dlq.base;

import java.util.HashMap;
import java.util.Map;

/**
 *@program: Java_interview
 *@description:
 *@author: Hasee
 *@create: 2021-05-13 17:03
 */
public class LRUCacheDemo2 {

    //map 负责查找，构建一个虚拟的双向链表，它里面安装的就是一个个Node节点，作为数据载体。
    //1 构造一个Node节点，作为数据载体
    class Node<k, v> {
        k key;
        v value;
        Node<k, v> prev;
        Node<k, v> next;

        public Node() {
            this.prev = this.next = null;
        }

        public Node(k key, v value) {
            this.key = key;
            this.value = value;
            this.prev = this.next = null;
        }
    }

    //2 构造一个虚拟的双向链表，里面安放的就是我们的Node
    class DoubleLinkedList<k, v> {
        Node<k, v> head;
        Node<k, v> tail;

        //2.1 构造方法
        public DoubleLinkedList() {
            head = new Node<>();
            tail = new Node<>();
            head.next = tail;
            tail.prev = head;
        }

        //2.2 添加到头
        public void addHead(Node<k, v> node) {
            node.next = tail;
            node.prev = head;
            head.next = node;
            tail.prev = node;
        }

        //2.3 删除节点
        public void removeNode(Node<k, v> node) {
            node.prev.next = node.next;
            node.next.prev = node.prev;
            node.next = null;
            node.prev = null;
        }

        //2.4 获得最后一个节点
        public Node getLast(){
            return tail.prev;
        }
    }

    private int cacheSize;
    Map<Integer,Node<Integer,Integer>> map;
    DoubleLinkedList<Integer,Integer> doubleLinkedList;

    public LRUCacheDemo2(int cacheSize){
        this.cacheSize = cacheSize; // 坑位
        map = new HashMap<>(); // 查找
        doubleLinkedList = new DoubleLinkedList<>();
    }

    public int get(int key){
        if (!map.containsKey(key)){
            return -1;
        }
        Node<Integer,Integer> node = map.get(key);
        doubleLinkedList.removeNode(node);
        doubleLinkedList.addHead(node);
        return node.value;
    }

    public void put(int key,int value){
        if (map.containsKey(key)){//update
            Node<Integer,Integer> node = map.get(key);
            node.value = value;
            map.put(key,node);

            doubleLinkedList.removeNode(node);
            doubleLinkedList.addHead(node);
        }else {
            if (map.size() == cacheSize){ //坑位满了
                Node<Integer,Integer> lastNode = doubleLinkedList.getLast();
                map.remove(lastNode.key);
                doubleLinkedList.removeNode(lastNode);
            }
            //才是新增一个 value
            Node<Integer, Integer> newNode = new Node<>(key ,value);
            map.put(key,newNode);
            doubleLinkedList.addHead(newNode);
        }
    }

    public static void main(String[] args) {
        LRUCacheDemo2 lruCacheDemo = new LRUCacheDemo2(3);
        lruCacheDemo.put(1,1);
        lruCacheDemo.put(2,2);
        lruCacheDemo.put(3,3);
        System.out.println(lruCacheDemo.map.keySet());

        lruCacheDemo.put(4,1);
        System.out.println(lruCacheDemo.map.keySet());
        System.out.println("|--------|");
        lruCacheDemo.put(3,1);
        System.out.println(lruCacheDemo.map.keySet());
        lruCacheDemo.put(3,1);
        System.out.println(lruCacheDemo.map.keySet());
        lruCacheDemo.put(3,1);
        System.out.println(lruCacheDemo.map.keySet());
        System.out.println("|--------|");
        lruCacheDemo.put(5,1);
        System.out.println(lruCacheDemo.map.keySet());
    }

}
