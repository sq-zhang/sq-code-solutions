package codeforces.round.round659.b1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static boolean solve(int[][] nums, int n, int k, int l) {
        for(int i = 0;i < k;i++) {
            if (dfs(nums, i, 0, n, l, k, new boolean[k][n])) {
                return true;
            }
        }
        return false;
    }

    static boolean dfs(int[][] nums, int s, int e, int n, int l, int k, boolean[][] vis) {
        if (e == n) {
            return true;
        }
        if (nums[s][e] > l || vis[s][e]) {
            return false;
        }
        vis[s][e] = true;
        s = (s + 1) % k;
        return dfs(nums, s, e + 1, n, l, k, vis) ||
                dfs(nums, s, e, n, l, k, vis);
    }

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt(), k = sc.nextInt(), l = sc.nextInt();
            int[][] nums = new int[2 * k][n];
            for(int i = 0;i < n;i++) {
                nums[0][i] = sc.nextInt();
            }
            for(int i = 1;i <= k;i++) {
                for(int j = 0;j < n;j++) {
                    nums[i][j] = nums[i - 1][j] + 1;
                }
            }
            for(int i = k + 1;i < 2 * k;i++) {
                for(int j = 0;j < n;j++) {
                    nums[i][j] = nums[i - 1][j] - 1;
                }
            }
            if (solve(nums, n, 2 * k, l)) {
                pw.println("Yes");
            } else {
                pw.println("No");            }
        }
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
        int[] nextArray(int n) {
            int[] a = new int[n];
            for(int i = 0;i < n;i++) {
                a[i] = nextInt();
            }
            return a;
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}