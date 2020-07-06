package multithread.H2O;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
public class ReentrantH2O {

    private ReentrantLock lock = new ReentrantLock();
    private Condition hCondition = lock.newCondition();
    private Condition oCondition = lock.newCondition();
    private int hRelease = 2;
    private int oRelease = 0;

    public ReentrantH2O() {

    }

    public void hydrogen(Runnable releaseHydrogen) throws InterruptedException {
        lock.lock();
        try {
            while (hRelease == 0) {
                hCondition.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseHydrogen.run();
            hRelease--;
            if (hRelease % 2 == 0) {
                oRelease++;
            }
            oCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public void oxygen(Runnable releaseOxygen) throws InterruptedException {
        lock.lock();
        try {
            while (oRelease == 0) {
                oCondition.await();
            }
            // releaseHydrogen.run() outputs "H". Do not change or remove this line.
            releaseOxygen.run();
            oRelease--;
            hRelease += 2;
            hCondition.signalAll();
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantH2O h2o = new ReentrantH2O();
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