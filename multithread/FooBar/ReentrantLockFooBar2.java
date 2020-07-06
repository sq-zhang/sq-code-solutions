package multithread.FooBar;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
class ReentrantLockFooBar2 {
    private static int n = 20;
    private static ReentrantLock lock = new ReentrantLock();
    private static Condition condition = lock.newCondition();
    //模拟生产者消费者模式，用于判断共享变量是否为空
    private static boolean isEmpty = true;

    static class FooThread extends Thread {

        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < n; i++) {
                    while(!isEmpty){
                        System.out.println("foo condition.await()");
                        condition.await();
                    }
                    System.out.println("===foo");
                    isEmpty = false;
                    System.out.println("foo condition.signal()");
                    condition.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

    static class BarThread extends Thread {
        @Override
        public void run() {
            lock.lock();
            try {
                for (int i = 0; i < n; i++) {
                    while(isEmpty){
                        System.out.println("bar condition.await()");
                        condition.await();
                    }
                    System.out.println("===bar");
                    isEmpty = true;
                    System.out.println("bar condition.signal()");
                    condition.signal();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        FooThread fooThread = new FooThread();
        BarThread barThread = new BarThread();

        barThread.start();
        fooThread.start();
    }
}