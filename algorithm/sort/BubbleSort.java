package algorithm.sort;

/**
 * @author sqzhang
 * @year 2020
 */
public class BubbleSort implements Sort {

    @Override
    public void sort(int[] nums) {
        for(int i = 0;i < nums.length - 1;i++) {
            for(int j = nums.length - 1;j > i;j--) {
                if (nums[j] < nums[j - 1]) {
                    swap(nums, j, j - 1);
                }
            }
        }
    }
}
