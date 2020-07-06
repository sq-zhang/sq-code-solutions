package multithread.FooBar;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
class BlockingQueueFooBar {
    private static int n = 20;
    private static BlockingQueue<Integer> queue = new LinkedBlockingDeque<>(1);

    static class FooThread extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < n; i++) {
                    queue.put(i);
                    System.out.println("===foo");
                    queue.put(i);
                    queue.put(i);
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
                    queue.take();
                    queue.take();
                    System.out.println("===bar");
                    queue.take();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        FooThread fooThread = new FooThread();
        BarThread barThread = new BarThread();

        barThread.start();
        fooThread.start();
    }
}