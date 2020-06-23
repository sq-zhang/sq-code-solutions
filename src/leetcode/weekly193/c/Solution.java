package leetcode.weekly193.c;

import com.sun.tools.javac.util.Assert;

/**
 * @author sqzhang
 * @date 2020/6/14
 * 给你一个整数数组 bloomDay，以及两个整数 m 和 k 。
 *
 * 现需要制作 m 束花。制作花束时，需要使用花园中 相邻的 k 朵花 。
 *
 * 花园中有 n 朵花，第 i 朵花会在 bloomDay[i] 时盛开，恰好 可以用于 一束 花中。
 *
 * 请你返回从花园中摘 m 束花需要等待的最少的天数。如果不能摘到 m 束花则返回 -1 。
 */
class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        if (m * k > bloomDay.length) {
            return -1;
        }
        int low = 0, high = Integer.MAX_VALUE;
        while (low < high) {
            int mid = low + ((high - low) >> 1);
            if (check(bloomDay, m, k, mid)) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    private boolean check(int[] bloomDay, int m, int k, int mid) {
        int count = 0, length = 0;
        for (int bloom : bloomDay) {
            if (bloom <= mid) {
                length++;
                if (length == k) {
                    count++;
                    length = 0;
                }
            } else {
                length = 0;
            }
        }
        return count >= m;
    }
    
    public static void test(int[] bloomDay, int m, int k, int target) {
        Solution solution = new Solution();
        Assert.check(solution.minDays(bloomDay, m, k) == target);
    }

    public static void main(String[] args) {
        test(new int[]{1,10,3,10,2}, 3, 1, 3);
        test(new int[]{7,7,7,7,12,7,7}, 2, 3, 12);
        test(new int[]{1, 10, 3, 10, 2}, 3, 2, -1);
        test(new int[]{1000000000,1000000000}, 1, 1, 1000000000);
        test(new int[]{1,10,2,9,3,8,4,7,5,6}, 4, 2, 9);
    }
}
