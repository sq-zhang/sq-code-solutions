package leetcode.weekly195.a;

/**
 * @author sqzhang
 * @date 2020/6/24
 */
class Solution {
    public int xorOperation(int n, int start) {
        int res = start;
        for(int i = 1;i < n;i++) {
            res ^= (start + 2 * i);
        }
        return res;
    }
}
