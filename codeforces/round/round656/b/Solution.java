package codeforces.round.round656.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = sc.nextArray(2 * n);
            boolean[] visited = new boolean[n + 1];
            int[] ans = new int[n + 1];
            int k = n;
            for(int i = 2 * n - 1;i >= 0;i--) {
                if (!visited[a[i]]) {
                    ans[k--] = a[i];
                    visited[a[i]] = true;
                }
            }
            for (int i = 1;i <= n;i++) {
                pw.print(ans[i] + " ");
            }
            pw.println();
            pw.flush();
        }
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