package datastructure.linkedlist;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sqzhang
 * @year 2020
 */
public class LRUCache {
    private Map<Integer, Node> cache;
    private int size;
    private int capacity;
    private Node head, tail;

    static class Node {
        int k, v;
        Node prev, next;
    }

    public LRUCache(int capacity) {
        this.size = 0;
        this.capacity = capacity;
        this.head = new Node();
        this.tail = new Node();
        this.head.next = this.tail;
        this.tail.prev = this.head;
        this.cache = new HashMap<>();
    }

    private void addNode(Node node) {
        node.prev = head;
        node.next = head.next;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(Node node) {
        Node prev = node.prev;
        Node next = node.next;
        prev.next = next;
        next.prev = prev;
    }

    private void moveToHead(Node node) {
        removeNode(node);
        addNode(node);
    }

    private Node popTail() {
        Node res = tail.prev;
        removeNode(res);
        return res;
    }

    public int get(int key) {
        Node node = this.cache.get(key);
        if (node == null) {
            return -1;
        }
        moveToHead(node);
        return node.v;
    }

    public void put(int key, int value) {
        Node node = this.cache.get(key);
        if (node == null) {
            Node newNode = new Node();
            newNode.v = value;
            newNode.k = key;
            this.cache.put(key, newNode);
            addNode(newNode);
            this.size++;
            if (size > this.capacity) {
                Node tail = popTail();
                this.cache.remove(tail.k);
                this.size--;
            }
        } else {
            node.v = value;
            moveToHead(node);
        }
    }

    public static void main(String[] args) {
        HashMap<String, Integer> map = new HashMap<>(1000);
        map.put("1", 1);
    }

}
