package leetcode.weekly196.b;

/**
 * @author sqzhang
 * @year 2020
 */
public class Solution {
    public int getLastMoment(int n, int[] left, int[] right) {
        int res = 0;
        for(int x : right){
            res = Math.max(res, n - x);
        }
        for(int x : left){
            res = Math.max(res, x);
        }
        return res;
    }
}
