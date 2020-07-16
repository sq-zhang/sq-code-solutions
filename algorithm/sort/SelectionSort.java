package algorithm.sort;

/**
 * @author sqzhang
 * @year 2020
 */
public class SelectionSort implements Sort {

    @Override
    public void sort(int[] nums) {
        for (int i = 0;i < nums.length;i++) {
            int min = i;
            for(int j = i + 1;j < nums.length;j++) {
                if (nums[j] < nums[min]) {
                    min = j;
                }
            }
            if (min != i) {
                swap(nums, i, min);
            }
        }
    }
}
