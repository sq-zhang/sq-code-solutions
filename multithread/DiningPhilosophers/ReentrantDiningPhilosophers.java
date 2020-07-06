package multithread.DiningPhilosophers;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sqzhang
 * @date 2020/6/13
 * 哲学家就餐问题解法一：防止死锁，一部分先拿左边，一部分先拿右边
 * 哲学家就餐问题解法二：防止死锁，最多允许 n - 1 同时拿叉子
 * 哲学家就餐问题解法三：防止死锁，只允许一个人拿，在拿前后加锁
 */
public class ReentrantDiningPhilosophers {

    private ReentrantLock[] forks = {new ReentrantLock(), new ReentrantLock(),
        new ReentrantLock(), new ReentrantLock(), new ReentrantLock()};

    private Semaphore eatLimit = new Semaphore(4);

    public ReentrantDiningPhilosophers() {

    }

    // call the run() method of any runnable to execute its code
    public void wantsToEat(int philosopher, Runnable pickLeftFork, Runnable pickRightFork,
        Runnable eat, Runnable putLeftFork, Runnable putRightFork) throws InterruptedException {
        int right = (philosopher + 1) % 5;

        eatLimit.acquire();
        forks[philosopher].lock();
        forks[right].lock();

        pickLeftFork.run();
        pickRightFork.run();

        eat.run();

        putLeftFork.run();
        putRightFork.run();

        forks[philosopher].unlock();
        forks[right].unlock();
        eatLimit.release();
    }
}