package multithread.ReadWriteLock;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sqzhang
 * @year 2020
 */
public class ReentrantReadWriteLock {

    private Map<Thread, Integer> readThreads = new HashMap<>();
    private int writerAccess = 0;
    private int writerRequest = 0;
    private Thread writerThread = null;

    public synchronized void lockRead() throws InterruptedException {
        Thread thread = Thread.currentThread();
        while (!canReadAccess(thread)) {
            wait();
        }
        readThreads.put(thread, readThreads.getOrDefault(thread, 0) + 1);
    }

    private boolean canReadAccess(Thread thread) {
        if (writerThread == thread) {
            return true;
        }
        if (writerAccess > 0) {
            return false;
        }
        if (readThreads.get(thread) != null) {
            return true;
        }
        return writerRequest <= 0;
    }

    public synchronized void unlockRead() {
        Thread thread = Thread.currentThread();
        if (readThreads.get(thread) == null) {
            throw new IllegalMonitorStateException("do not hold lock");
        }
        int count = readThreads.get(thread);
        if (count == 1) {
            readThreads.remove(thread);
        } else {
            readThreads.put(thread, count - 1);
        }
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writerRequest++;
        Thread thread = Thread.currentThread();
        while (!canWriteAccess(thread)) {
            wait();
        }
        writerRequest--;
        writerAccess++;
        writerThread = thread;
    }

    public boolean canWriteAccess(Thread thread) {
        if (readThreads.size() == 1 && readThreads.get(thread) != null) {
            return true;
        }
        if (readThreads.size() > 0) {
            return false;
        }
        if (writerThread == null) {
            return true;
        }
        return writerThread == thread;
    }

    public synchronized void unlockWrite() {
        if (Thread.currentThread() != writerThread) {
            throw new IllegalMonitorStateException("do not hold lock");
        }
        writerAccess--;
        if (writerAccess == 0) {
            writerThread = null;
        }
        notifyAll();
    }

}
