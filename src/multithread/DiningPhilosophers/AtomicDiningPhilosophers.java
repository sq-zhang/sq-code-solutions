package multithread.DiningPhilosophers;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sqzhang
 * @date 2020/6/13
 * 哲学家就餐问题解法一：防止死锁，一部分先拿左边，一部分先拿右边
 * 哲学家就餐问题解法二：防止死锁，最多允许 n - 1 同时拿叉子
 * 哲学家就餐问题解法三：防止死锁，只允许一个人拿，在拿前后加锁
 */
public class AtomicDiningPhilosophers {
    private AtomicInteger fork = new AtomicInteger(0);
    private AtomicInteger lock = new AtomicInteger(0);
    private final int[] forkMask = new int[]{1, 2, 4, 8, 16};

    public AtomicDiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork,
        Runnable eat, Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {

        int leftMask = forkMask[(philosopher + 1) % 5], rightMask = forkMask[philosopher];

        while (!lock.compareAndSet(0, 1)) {
            Thread.sleep(1);
        }
        while (!pickFork(leftMask)) {
            Thread.sleep(1);
        }
        while (!pickFork(rightMask)) {
            Thread.sleep(1);
        }
        while (!lock.compareAndSet(1, 0)) {
            Thread.sleep(1);
        }

        pickLeftFork.run();
        pickRightFork.run();

        eat.run();

        putLeftFork.run();
        putRightFork.run();

        while (!putFork(rightMask)) {
            Thread.sleep(1);
        }
        while (!putFork(leftMask)) {
            Thread.sleep(1);
        }
    }

    private boolean pickFork(int mask) {
        int expect = fork.get();
        return (expect & mask) <= 0 && fork.compareAndSet(expect, expect ^ mask);
    }

    private boolean putFork(int mask) {
        int expect = fork.get();
        return fork.compareAndSet(expect, expect ^ mask);
    }
}