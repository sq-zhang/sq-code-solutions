package algorithm.sort;

/**
 * @author sqzhang
 * @year 2020
 */
public class QuickSort implements Sort {

    @Override
    public void sort(int[] nums) {
        quickSortHelper(nums, 0, nums.length - 1);
    }

    private void quickSortHelper(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int pos = quickSortPartition(nums, left, right);
        quickSortHelper(nums, left, pos - 1);
        quickSortHelper(nums, pos + 1, right);
    }

    private int quickSortPartition(int[] nums, int left, int right) {
        int pivot = nums[left];
        while (left < right) {
            while (left < right && nums[right] >= pivot) {
                right--;
            }
            nums[left] = nums[right];
            while (left < right && nums[left] <= pivot) {
                left++;
            }
            nums[right] = nums[left];
        }
        nums[left] = pivot;
        return left;
    }
}
