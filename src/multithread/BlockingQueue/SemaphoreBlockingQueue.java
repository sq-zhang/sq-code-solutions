package multithread.BlockingQueue;

import java.util.LinkedList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sqzhang
 * @date 2020/6/13
 */
public class SemaphoreBlockingQueue {
    private LinkedList<Integer> queue = new LinkedList<>();
    private Semaphore consumeSemaphore;
    private Semaphore produceSemaphore;
    private final int capacity;

    public SemaphoreBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.consumeSemaphore = new Semaphore(0);
        this.produceSemaphore = new Semaphore(capacity);
    }

    public void enqueue(int element) throws InterruptedException {
        produceSemaphore.acquire();
        queue.add(element);
        consumeSemaphore.release();
    }

    public int dequeue() throws InterruptedException {
        consumeSemaphore.acquire();
        int v = queue.removeFirst();
        produceSemaphore.release();
        return v;
    }

    public int size() {
        return queue.size();
    }
}