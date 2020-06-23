package multithread.FizzBuzz;

import java.util.function.IntConsumer;

/**
 * @author sqzhang
 * @date 2020/6/12
 */
public class VolatileFizzBuzz {
    private int n;
    private volatile int state = -1;

    public VolatileFizzBuzz(int n) {
        this.n = n;
    }

    public void fizz(Runnable printFizz) throws InterruptedException {
        for (int i = 3; i <= n; i += 3) {
            if (i % 15 == 0) {
                continue;
            }
            while (state != 3) {
                Thread.yield();
            }
            printFizz.run();
            state = -1;
        }
    }

    public void buzz(Runnable printBuzz) throws InterruptedException {
        for (int i = 5; i <= n; i += 5) {
            if (i % 15 == 0){
                continue;
            }
            while (state != 5){
                Thread.yield();
            }

            printBuzz.run();
            state = -1;
        }
    }

    public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
        for (int i = 15; i <= n; i += 15) {
            while (state != 15){
                Thread.yield();
            }

            printFizzBuzz.run();
            state = -1;
        }
    }

    public void number(IntConsumer printNumber) throws InterruptedException {
        for (int i = 1; i <= n; ++i) {
            while (state != -1) {
                Thread.yield();
            }
            if (i % 3 != 0 && i % 5 != 0) {
                printNumber.accept(i);
            } else if (i % 15 == 0) {
                state = 15;
            } else if (i % 5 == 0) {
                state = 5;
            } else {
                state = 3;
            }
        }
    }

    public static void main(String[] args) {
        VolatileFizzBuzz fizzBuzz = new VolatileFizzBuzz(30);
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
