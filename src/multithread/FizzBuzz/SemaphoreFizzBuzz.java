package multithread.FizzBuzz;

import java.util.concurrent.Semaphore;
import java.util.function.IntConsumer;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
public class SemaphoreFizzBuzz {
    private int n;
    private Semaphore s, s1, s2, s3;

    public SemaphoreFizzBuzz(int n) {
        this.n = n;
        s = new Semaphore(1);
        s1 = new Semaphore(0);
        s2 = new Semaphore(0);
        s3 = new Semaphore(0);
    }

    // printFizz.run() outputs "fizz".
    public void fizz(Runnable printFizz) throws InterruptedException {
        for(int i = 3;i <= n;i += 3) {
            if (i % 5 == 0) {
                continue;
            }
            s3.acquire();
            printFizz.run();
            s.release();
        }
    }

    // printBuzz.run() outputs "buzz".
    public void buzz(Runnable printBuzz) throws InterruptedException {
        for(int i = 5;i <= n;i += 5) {
            if (i % 3 == 0) {
                continue;
            }
            s2.acquire();
            printBuzz.run();
            s.release();
        }
    }

    // printFizzBuzz.run() outputs "fizzbuzz".
    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for(int i = 15;i <= n;i += 15) {
            s1.acquire();
            printFizzBuzz.run();
            s.release();
        }
    }

    // printNumber.accept(x) outputs "x", where x is an integer.
    public void number(IntConsumer printNumber) throws InterruptedException {
        for(int i = 1;i <= n;i++) {
            s.acquire();
            if (i % 3 == 0 && i % 5 == 0) {
                s1.release();
            } else if (i % 5 == 0) {
                s2.release();
            } else if (i % 3 == 0) {
                s3.release();
            } else {
                printNumber.accept(i);
                s.release();
            }
        }
    }

    public static void main(String[] args) {
        SemaphoreFizzBuzz fizzBuzz = new SemaphoreFizzBuzz(30);
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
