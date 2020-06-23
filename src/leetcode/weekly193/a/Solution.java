package leetcode.weekly193.a;

import com.sun.tools.javac.util.Assert;
import java.util.Arrays;

/**
 * @author sqzhang
 * @date 2020/6/14
 * 给你一个数组 nums 。数组「动态和」的计算公式为：runningSum[i] = sum(nums[0]…nums[i]) 。
 *
 * 请返回 nums 的动态和。
 */
class Solution {
    public static int[] runningSum(int[] nums) {
        int n = nums.length;
        if (n == 0) {
            return new int[0];
        }
        int[] sum = new int[n];
        sum[0] = nums[0];
        for(int i = 1;i < n;i++) {
            sum[i] = sum[i - 1] + nums[i];
        }
        return sum;
    }

    public static void test(int[] nums, int[] target) {
        Assert.check(Arrays.equals(runningSum(nums), target));
    }

    public static void main(String[] args) {
        test(new int[]{1, 2, 3, 4}, new int[]{1, 3, 6, 10});
    }
}
