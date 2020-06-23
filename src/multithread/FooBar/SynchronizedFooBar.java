package multithread.FooBar;


/**
 * @author sqzhang
 * @date 2020/6/12
 */
class SynchronizedFooBar {
    private static int n = 20;
    // 这里不需要加 volatile，因为同一时间只有一个线程访问 fooTurn
    private static boolean fooTurn = true;
    private static final Object lock = new Object();

    static class FooThread extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < n; i++) {
                    synchronized(lock) {
                        if (!fooTurn) {
                            lock.wait();
                        }
                        fooTurn = false;
                        System.out.println("===foo");
                        lock.notifyAll();
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class BarThread extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < n; i++) {
                    synchronized(lock) {
                        if (fooTurn) {
                            lock.wait();
                        }
                        fooTurn = true;
                        System.out.println("===bar");
                        lock.notifyAll();
                    }
                }
            } catch (Exception e) {
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