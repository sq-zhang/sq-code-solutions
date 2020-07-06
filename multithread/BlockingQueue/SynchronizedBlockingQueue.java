package multithread.BlockingQueue;

import java.util.LinkedList;

/**
 * @author sqzhang
 * @date 2020/6/13
 */
public class SynchronizedBlockingQueue {
    private LinkedList<Integer> list = new LinkedList<>();
    private final int capacity;
    private int size = 0;

    public SynchronizedBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public synchronized void enqueue(int element) throws InterruptedException {
        while(size >= capacity) {
            wait();
        }
        list.addFirst(element);
        size++;
        notifyAll();
    }

    public synchronized int dequeue() throws InterruptedException {
        while(size == 0) {
            wait();
        }
        int v = list.removeLast();
        size--;
        notifyAll();
        return v;
    }

    public int size() {
        return size;
    }
}