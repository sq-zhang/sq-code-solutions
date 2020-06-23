package multithread.H2O;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Semaphore;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
public class CyclicBarrierH2O {

    private CyclicBarrier barrier = new CyclicBarrier(3, new Runnable() {
        @Override
        public void run() {
            semaphoreH.release(2);
            semaphoreO.release();
        }
    });

    private Semaphore semaphoreH;
    private Semaphore semaphoreO;

    public CyclicBarrierH2O() {
        semaphoreH = new Semaphore(2);
        semaphoreO = new Semaphore(1);
    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        semaphoreH.acquire();
        // releaseHydrogen.run() outputs "H". Do not change or remove this line.
        releaseHydrogen.run();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        semaphoreO.acquire();
        // releaseOxygen.run() outputs "O". Do not change or remove this line.
        releaseOxygen.run();
        try {
            barrier.await();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CyclicBarrierH2O h2o = new CyclicBarrierH2O();
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