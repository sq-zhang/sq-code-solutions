package multithread.FooBar;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
class ThreadYieldFooBar {
    private static int n = 20;
    static volatile boolean printFooCondition = true;
    static class FooThread extends Thread {

        @Override
        public void run() {
            try {
                for (int i = 0; i < n; i++) {
                    while(!printFooCondition) {
                        Thread.yield();
                    }
                    System.out.println("===foo");
                    printFooCondition = false;
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
                    while(printFooCondition) {
                        Thread.yield();
                    }
                    System.out.println("===bar");
                    printFooCondition = true;
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