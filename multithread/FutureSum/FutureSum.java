package multithread.FutureSum;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.List;

/**
 * @author sqzhang
 * @date 2020/6/13
 */
public class FutureSum {
    private static int processors = Runtime.getRuntime().availableProcessors();
    private static ExecutorService pool = Executors.newFixedThreadPool(processors);

    private static class SumTask implements Callable<Long> {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        public Long call() {
            long total = 0;
            for (int i = from; i <= to; i++) {
                total += numbers[i];
            }
            return total;
        }
    }
    
    private static Long parallelSum(long[] nums) {
        List<Future<Long>> results = new ArrayList<>();
        int part = nums.length / processors;
        for (int i = 0; i < processors; i++) {
            int from = i * part;
            int to = (i == processors - 1) ? nums.length - 1 : (i + 1) * part - 1;
            results.add(pool.submit(new SumTask(nums, from, to)));
        }

        long res = 0L;
        for (Future<Long> f : results) {
            try {
                res += f.get();
            } catch (Exception ignore) {}
        }
        pool.shutdown();
        return res;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int N = 1000000, START = 0, END = 1000;
        long[] nums = rand.longs(N, START, END).toArray();
        long t0 = System.currentTimeMillis();
        long res = parallelSum(nums);
        long t1 = System.currentTimeMillis();
        System.out.println("parallelSum = " + res + " time = " + (t1 - t0));
    }
}
