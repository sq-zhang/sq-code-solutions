package codeforces.round.round656.a;

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
            int x = sc.nextInt(), y = sc.nextInt(), z = sc.nextInt();
            if (x == y && x == z) {
                pw.println("YES");
                pw.println(x + " " + x + " " + x);
                continue;
            }
            if (x == y) {
                if (z < x) {
                    pw.println("YES");
                    pw.println(x + " " + z + " " + z);
                } else {
                    pw.println("NO");
                }
            } else if (z != Math.max(x, y)) {
                pw.println("NO");
            } else {
                pw.println("YES");
                pw.println(x + " " + y + " 1");
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