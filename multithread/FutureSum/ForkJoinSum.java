package multithread.FutureSum;

import java.util.Random;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * @author sqzhang
 * @year 2020
 */
public class ForkJoinSum {

    static class SumTask extends RecursiveTask<Long> {
        private static int THRESHOLD = 10000000 / 2;
        private int start, end;
        private long[] nums;

        public SumTask(long[] nums, int start, int end) {
            this.nums = nums;
            this.start = start;
            this.end = end;
        }

        @Override
        protected Long compute() {
            long sum = 0;
            if (end - start <= THRESHOLD) {
                for (int i = start;i <= end;i++) {
                    sum += nums[i];
                }
            } else {
                int mid = start + (end - start) / 2;
                SumTask leftTask = new SumTask(nums, start, mid);
                SumTask rightTask = new SumTask(nums, mid + 1, end);
                leftTask.fork();
                rightTask.fork();
                long leftResult = leftTask.join();
                long rightResult = rightTask.join();
                sum = leftResult + rightResult;
            }
            return sum;
        }
    }

    private static long sumUp(long[] nums) {
        long sum = 0;
        for (int i = 0;i < nums.length;i++) {
            sum += nums[i];
        }
        return sum;
    }

    public static void main(String[] args) {
        Random rand = new Random();
        int N = 1000000;
        long[] nums = rand.longs(N).toArray();
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        long t0 = System.currentTimeMillis();
        SumTask sumTask = new SumTask(nums, 0, nums.length - 1);
        long res = forkJoinPool.invoke(sumTask);

        long t1 = System.currentTimeMillis();
        System.out.println("ForkJoinPoolSum = " + res + " time = " + (t1 - t0));
        res = sumUp(nums);
        long t2 = System.currentTimeMillis();
        System.out.println("SumUp = " + res + " time = " + (t2 - t1));
    }
}
