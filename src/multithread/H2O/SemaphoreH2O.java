package multithread.H2O;

import java.util.concurrent.Semaphore;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
public class SemaphoreH2O {
    private Semaphore h = new Semaphore(2);
    private Semaphore o = new Semaphore(0);

    public SemaphoreH2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        h.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        o.release();
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        o.acquire(2);
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        h.release(2);
    }

    public static void main(String[] args) {
        SemaphoreH2O h2o = new SemaphoreH2O();
        int n = 10;
        for (int i = 0; i < 2 * n; i++) {
            new Thread(() -> {
                try {
                    h2o.hydrogen(() -> System.out.print("H"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i < n; i++) {
            new Thread(() -> {
                try {
                    h2o.oxygen(() -> System.out.print("O"));
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}