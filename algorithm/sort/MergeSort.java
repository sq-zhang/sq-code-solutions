package algorithm.sort;

/**
 * @author sqzhang
 * @year 2020
 */
public class MergeSort implements Sort {

    @Override
    public void sort(int[] nums) {
        mergeSortHelper(nums, 0, nums.length - 1);
    }

    private void mergeSortHelper(int[] nums, int left, int right) {
        if (left >= right) {
            return;
        }
        int mid = (left + right) / 2;
        mergeSortHelper(nums, left, mid);
        mergeSortHelper(nums, mid + 1, right);
        mergeSortMerge(nums, left, mid, right);
    }

    private void mergeSortMerge(int[] nums, int left, int mid, int right) {
        int[] arr = new int[right - left + 1];
        int i = left, j = mid + 1, k = 0;
        while(i <= mid || j <= right) {
            if (i > mid) {
                arr[k++] = nums[j++];
            } else if (j > right) {
                arr[k++] = nums[i++];
            } else if (nums[i] <= nums[j]) {
                arr[k++] = nums[i++];
            } else {
                arr[k++] = nums[j++];
            }
        }
        for(int p = 0;p < arr.length;p++) {
            nums[left + p] = arr[p];
        }
    }
}
