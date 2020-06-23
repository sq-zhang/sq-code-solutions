package multithread.FooBar;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
class CountDownLatchFooBar {
    private static int n = 20;
    private static CountDownLatch countDown = new CountDownLatch(1);
    private static CyclicBarrier barrier = new CyclicBarrier(2);

    static class FooThread extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < n; i++) {
                    System.out.println("===foo");
                    countDown.countDown();
                    barrier.await();
                }
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

    static class BarThread extends Thread {
        @Override
        public void run() {
            try {
                for (int i = 0; i < n; i++) {
                    countDown.await();
                    System.out.println("===bar");
                    countDown = new CountDownLatch(1);
                    barrier.await();
                }
            } catch (InterruptedException | BrokenBarrierException e) {
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