package multithread.ZeroOddEven;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
public class ReentrantZeroEvenOdd {
    private int n;
    private volatile int state = 0;
    private volatile int i = 1;
    private ReentrantLock lock = new ReentrantLock();
    private Condition zero = lock.newCondition();
    private Condition odd = lock.newCondition();
    private Condition even = lock.newCondition();

    public ReentrantZeroEvenOdd(int n) {
        this.n = n;
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void zero(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try{
            while(i <= n) {
                if (state != 0) {
                    zero.await();
                }
                printNumber.accept(0);
                if (i % 2 == 0) {
                    state = 2;
                    even.signal();
                } else {
                    state = 1;
                    odd.signal();
                }
                zero.await();
            }
            odd.signal();
            even.signal();
        } finally {
            lock.unlock();
        }
    }

    public void even(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try{
            while(i <= n) {
                if (state != 2) {
                    even.await();
                } else {
                    printNumber.accept(i++);
                    state = 0;
                    zero.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public void odd(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try{
            while(i <= n) {
                if (state != 1) {
                    odd.await();
                } else {
                    printNumber.accept(i++);
                    state = 0;
                    zero.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantZeroEvenOdd zeroEvenOdd = new ReentrantZeroEvenOdd(6);
        for (int i = 0; i < 4; i++) {
            new Thread(() -> {
                try {
                    zeroEvenOdd.zero(System.out::print);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i <2; i++) {
            new Thread(() -> {
                try {
                    zeroEvenOdd.even(System.out::print);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
        for (int i = 0; i <2; i++) {
            new Thread(() -> {
                try {
                    zeroEvenOdd.odd(System.out::print);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }
}
