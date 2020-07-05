package leetcode.weekly196.a;

import java.util.Arrays;

/**
 * @author sqzhang
 * @year 2020
 */
public class Solution {
    public boolean canMakeArithmeticProgression(int[] arr) {
        if (arr.length == 2) {
            return true;
        }
        Arrays.sort(arr);
        for(int i = 1;i < arr.length - 1;i++) {
            if (arr[i] - arr[i - 1] != arr[i + 1] - arr[i]) {
                return false;
            }
        }
        return true;
    }
}
