package datastructure.queue;

import datastructure.linkedlist.DoubleLinkedList;

/**
 * an implementation of Linked Queue
 * @author sqzhang
 * @year 2020
 */
public class LinkedQueue<T> implements Queue<T> {

    DoubleLinkedList<T> list = new DoubleLinkedList<>();

    public LinkedQueue() {}

    public LinkedQueue(T ele) {
        offer(ele);
    }

    @Override
    public void offer(T ele) {
        list.addLast(ele);
    }

    @Override
    public T poll() {
        return list.removeFirst();
    }

    @Override
    public T peek() {
        return list.peekFirst();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    public static void main(String[] args) {

        LinkedQueue<Integer> q = new LinkedQueue<>(5);

        q.offer(1);
        q.offer(2);
        q.offer(3);
        q.offer(4);
        q.offer(5);

        System.out.println(q.poll()); // 1
        System.out.println(q.poll()); // 2
        System.out.println(q.poll()); // 3
        System.out.println(q.poll()); // 4

        System.out.println(q.isEmpty()); // false

        q.offer(1);
        q.offer(2);
        q.offer(3);

        System.out.println(q.poll()); // 5
        System.out.println(q.poll()); // 1
        System.out.println(q.poll()); // 2
        System.out.println(q.poll()); // 3

        System.out.println(q.isEmpty()); // true
    }
}
