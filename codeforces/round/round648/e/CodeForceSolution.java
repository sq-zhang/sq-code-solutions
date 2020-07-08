package codeforces.round.round648.e;

import java.util.*;
import java.io.*;
/**
 * @author sqzhang
 * @date 2020/6/11
 * E. Maximum Subsequence Value
 * 题目描述：给定数组 a，求 a 的子序列条件按位或的最大值
 * 条件按位或是指，序列里至少有 max(1, k -2) 的数在 i 位为 1，i 位才能为 1
 */
public class CodeForceSolution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        long[] nums = new long[n];
        for(int i = 0;i < n;i++) {
            nums[i] = sc.nextLong();
        }
        long res = 0;
        for(int i = 0;i < n;i++) {
            for(int j = i;j < n;j++) {
                for(int k = j;k < n;k++) {
                    res = Math.max(res, (nums[i] | nums[j] | nums[k]));
                }
            }
        }
        pw.println(res);
        pw.flush();
    }

    static class FS {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        String next() {
            while(!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch(Exception ignored) {}
            }
            return st.nextToken();
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}