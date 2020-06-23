package multithread.FooBar;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
class ReentrantLockFooBar {
    private static int n = 20;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition fooCondition = lock.newCondition();
    private static int count = 1;

    static class FooThread extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < n; i++) {
                    lock.lock();
                    System.out.println("foo lock.lock()");
                    if (count != 1) {
                        System.out.println("foo fooCondition.await()");
                        fooCondition.await();
                    }
                    System.out.println("===foo");
                    System.out.println("foo barCondition.signal()");
                    fooCondition.signal();
                    count = 2;
                    System.out.println("foo lock.unlock()");
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class BarThread extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < n; i++) {
                    lock.lock();
                    System.out.println("bar lock.lock()");
                    if (count != 2) {
                        System.out.println("bar barCondition.await()");
                        fooCondition.await();
                    }
                    System.out.println("===bar");
                    System.out.println("bar fooCondition.signal()");
                    fooCondition.signal();
                    count = 1;
                    System.out.println("bar lock.unlock()");
                    lock.unlock();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FooThread fooThread = new FooThread();
        BarThread barThread = new BarThread();
        fooThread.start();
        barThread.start();

    }
}