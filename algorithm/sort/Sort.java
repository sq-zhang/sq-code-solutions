package algorithm.sort;

/**
 * @author sqzhang
 * @year 2020
 */
public interface Sort {

    void sort(int[] nums);

    default void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
