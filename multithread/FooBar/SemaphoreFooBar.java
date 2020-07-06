package multithread.FooBar;

import java.util.concurrent.Semaphore;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
class SemaphoreFooBar {
    private static int n = 20;
    private static Semaphore foo = new Semaphore(1);
    private static Semaphore bar = new Semaphore(0);

    static class FooThread extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < n; i++) {
                    foo.acquire();
                    System.out.print("foo");
                    bar.release();
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
                    bar.acquire();
                    System.out.print("bar");
                    foo.release();
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