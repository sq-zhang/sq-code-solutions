package multithread.FutureSum;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;

/**
 * @author sqzhang
 * @date 2020/6/13
 */
public class FutureSum {

    static Random rand = new Random();

    static class RandomNum implements Callable<Integer> {

        @Override
        public Integer call() throws Exception {
            return rand.nextInt(100);
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        List<Future<Integer>> futureSum = new ArrayList<>();
        for(int i = 0;i < 100;i++) {
            futureSum.add(executorService.submit(new RandomNum()));
        }

        int sum = 0;
        for (Future<Integer> integerFuture : futureSum) {
            System.out.println(integerFuture.get());
            sum += integerFuture.get();
        }
        System.out.println("future sum is :" + sum);
    }
}
