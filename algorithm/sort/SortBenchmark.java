package algorithm.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;

/**
 * @author sqzhang
 * @year 2020
 */
public class SortBenchmark {

    static int[] TEST_SIZE = {10, 100, 1000, 10000, 100000, 1000000};
    static Random random = new Random();

    public static void main(String[] args) {
        List<Sort> sortList = new ArrayList<>();
        sortList.add(new HeapSort());
        sortList.add(new MergeSort());
        sortList.add(new QuickSort());
        sortList.add(new SelectionSort());
        sortList.add(new BubbleSort());
        sortList.add(new InsertionSort());

        for (Sort sort : sortList) {
            System.out.println(sort.getClass().getCanonicalName());
            for (int n : TEST_SIZE) {
                long start = System.currentTimeMillis();
                benchmark(1, n, sort);
                long end = System.currentTimeMillis();
                System.out.printf("%5d : %d \n", n, end - start);
            }
        }

    }

    private static void benchmark(int m, int n, Sort sort) {
        int[] nums;
        for(int i = 0;i < m;i++) {
            nums = randomArray(n);
            sort.sort(nums);
            for (int j = 1;j < n;j++) {
                if (nums[j] < nums[j - 1]) {
                    System.out.println(Arrays.toString(nums));
                }
            }
        }
    }

    private static int[] randomArray(int n) {
        int[] nums = new int[n];
        for (int i = 0;i < n;i++) {
            nums[i] = random.nextInt(n);
        }
        return nums;
    }
}
