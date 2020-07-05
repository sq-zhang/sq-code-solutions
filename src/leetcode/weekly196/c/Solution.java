package leetcode.weekly196.c;

import java.util.Arrays;

/**
 * @author sqzhang
 * @year 2020
 */
public class Solution {
    public int numSubmat(int[][] mat) {
        int n = mat.length, m = mat[0].length;
        int res = 0;
        for (int i = 0; i < n; i++){
            int[] sum = new int[m];
            for (int j = i; j < n; j++){
                for (int k = 0; k < m; k++) {
                    sum[k] += mat[j][k];
                }
                int cur = j - i + 1, cnt = 0;
                for (int k = 0; k < m; k++){
                    if (sum[k] == cur){
                        ++cnt;
                        res += cnt;
                    } else {
                        cnt = 0;
                    }
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
//        int[][] m = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
//        int[][] m = {{0, 1, 1, 0}, {0, 1, 1, 1}, {1, 1, 1, 0}};
        int[][] m = {
            {1,0,0,1,0,1,0,1},
            {1,0,1,1,0,1,0,0},
            {1,1,1,0,1,0,0,1},
            {0,0,1,1,1,1,0,0},
            {0,0,1,1,1,1,0,1},
            {1,1,0,1,1,1,0,0},
        };
        solution.numSubmat(m);

    }
}
