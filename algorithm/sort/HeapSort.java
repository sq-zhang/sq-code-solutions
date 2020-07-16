package algorithm.sort;

import java.util.Arrays;
import java.util.Random;

/**
 * @author sqzhang
 * @year 2020
 */
public class HeapSort implements Sort {

    @Override
    public void sort(int[] nums) {
        int n = nums.length;
        for (int i = n / 2;i >= 0;i--) {
            heapAdjust(nums, i, n - 1);
        }
        for (int i = n - 1;i >= 0;i--) {
            swap(nums, 0, i);
            heapAdjust(nums, 0, i - 1);
        }
    }

    private void heapAdjust(int[] nums, int s, int e) {
        while (s < e) {
            int max = s, left = 2 * s, right = 2 * s + 1;
            if (left <= e && nums[s] < nums[left]) {
                max = left;
            }
            if (right <= e && nums[max] < nums[right]) {
                max = right;
            }
            if (max == s) {
                break;
            }
            swap(nums, s, max);
            s = max;
        }
    }

    public static void main(String[] args) {
        Random rand = new Random();
        HeapSort heapSort = new HeapSort();
        int n = 10000;
        int[] nums = new int[n];
        for(int i = 0;i < n;i++) {
            nums[i] = rand.nextInt();
            heapSort.sort(nums);
            for (int j = 1;j < n;j++) {
                if (nums[j] < nums[j - 1]) {
                    System.out.println(Arrays.toString(nums));
                }
            }
        }
    }
}
