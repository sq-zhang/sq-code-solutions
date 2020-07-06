package multithread.ZeroOddEven;

import java.util.function.IntConsumer;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
public class SynchronizedZeroEvenOdd {
    private int n;
    private boolean zero = false;
    private boolean even = false;

    public SynchronizedZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public synchronized void zero(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1;i <= n;i++) {
            while(zero) {
                this.wait();
            }
            printNumber.accept(0);
            zero = true;
            this.notifyAll();
        }
    }

    public synchronized void even(IntConsumer printNumber) throws InterruptedException {
        for(int i = 2;i <= n;i += 2) {
            while (!zero || !even) {
                this.wait();
            }
            printNumber.accept(i);
            zero = false;
            even = false;
            this.notifyAll();
        }
    }

    public synchronized void odd(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1;i <= n;i += 2) {
            while (!zero || even) {
                this.wait();
            }
            printNumber.accept(i);
            zero = false;
            even = true;
            this.notifyAll();
        }
    }

    public static void main(String[] args) {
        SynchronizedZeroEvenOdd zeroEvenOdd = new SynchronizedZeroEvenOdd(9);
        new Thread(() -> {
            try {
                zeroEvenOdd.zero(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.even(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
        new Thread(() -> {
            try {
                zeroEvenOdd.odd(System.out::print);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
