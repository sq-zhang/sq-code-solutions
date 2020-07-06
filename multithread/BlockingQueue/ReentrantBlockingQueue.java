package multithread.BlockingQueue;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sqzhang
 * @date 2020/6/13
 */
public class ReentrantBlockingQueue {
    private LinkedList<Integer> queue = new LinkedList<>();
    private ReentrantLock lock=new ReentrantLock();
    private Condition empty = lock.newCondition();
    private Condition full = lock.newCondition();
    private int size = 0;
    private final int capacity;

    public ReentrantBlockingQueue(int capacity) {
        this.capacity = capacity;
    }

    public void enqueue(int element) throws InterruptedException {
        lock.lock();
        try{
            while(size >= capacity){
                full.await();
            }
            queue.offerFirst(element);
            size++;
            empty.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public int dequeue() throws InterruptedException {
        lock.lock();
        Integer res;
        try {
            while(size == 0){
                empty.await();
            }
            res = queue.pollLast();
            size -= 1;
            full.signalAll();
            return res;
        } finally {
            lock.unlock();
        }
    }

    public int size() {
        return size;
    }
}