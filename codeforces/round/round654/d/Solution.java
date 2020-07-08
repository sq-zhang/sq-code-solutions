package codeforces.round.round654.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt(), k = sc.nextInt();
            if (k % n == 0) {
                pw.println(0);
            } else {
                pw.println(2);
            }
            int[][] res = new int[n][n];
            int p = 0, q = 0;
            while(k > 0) {
                k--;
                res[p][q] = 1;
                p++;
                q = (q + 1) % n;
                if (p == n) {
                    p = 0;
                    q = (q + 1) % n;
                }
            }
            for(int i = 0;i < n;i++) {
                for(int j = 0;j < n;j++) {
                    pw.print(res[i][j]);
                }
                pw.println();
            }
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