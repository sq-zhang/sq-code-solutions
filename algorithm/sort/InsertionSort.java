package algorithm.sort;

/**
 * @author sqzhang
 * @year 2020
 */
public class InsertionSort implements Sort {

    @Override
    public void sort(int[] nums) {
        for (int i = 0;i < nums.length;i++) {
            int j = i, num = nums[i];
            while (j > 0 && nums[j - 1] > num) {
                nums[j] = nums[j - 1];
                j--;
            }
            nums[j] = num;
        }
    }
}
