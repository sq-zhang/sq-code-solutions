package multithread.FizzBuzz;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.IntConsumer;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
public class ReentrantFizzBuzz {
    private int n;
    private ReentrantLock lock = new ReentrantLock();
    private Condition fCond = lock.newCondition();
    private Condition bCond = lock.newCondition();
    private Condition fbCond = lock.newCondition();
    private Condition nCond = lock.newCondition();
    private volatile Boolean state = false;

    public ReentrantFizzBuzz(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 3; i <= n; i = i + 3) {
                if (i % 5 != 0) {
                    while (!state) {
                        fCond.await();
                    }
                    printFizz.run();
                    state = false;
                    nCond.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 5; i <= n; i = i + 5) {
                if (i % 3 != 0) {
                    while (!state) {
                        bCond.await();
                    }
                    printBuzz.run();
                    state = false;
                    nCond.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 15; i <= n; i = i + 15) {
                while (!state) {
                    fbCond.await();
                }
                printFizzBuzz.run();
                state = false;
                nCond.signal();
            }
        } finally {
            lock.unlock();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        lock.lock();
        try {
            for (int i = 1; i <= n; i++) {
                while (state) {
                    nCond.await();
                }
                if (i % 3 == 0 && i % 5 == 0) {
                    fbCond.signal();
                    state = true;
                }else if (i % 3 == 0) {
                    fCond.signal();
                    state = true;
                }else if (i % 5 == 0) {
                    bCond.signal();
                    state = true;
                }else {
                    printNumber.accept(i);
                    nCond.signal();
                }
            }
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args) {
        ReentrantFizzBuzz fizzBuzz = new ReentrantFizzBuzz(30);
        new Thread(() -> {
            try {
                fizzBuzz.buzz(() -> System.out.println("buzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fizzBuzz.fizz(() -> System.out.println("fizz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fizzBuzz.fizzbuzz(() -> System.out.println("fizzbuzz"));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            try {
                fizzBuzz.number(System.out::println);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
