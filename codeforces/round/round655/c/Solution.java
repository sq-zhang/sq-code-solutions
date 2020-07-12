package codeforces.round.round655.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = sc.nextArray(n);
            if (isSorted(a)) {
                pw.println(0);
                continue;
            }
            int ans = 1, state = 0;
            for (int i = 0;i < n;i++) {
                if (a[i] == i + 1) {
                    if (state == 1) {
                        state = 2;
                    }
                } else {
                    if (state == 0) {
                        state = 1;
                    } else if (state == 2) {
                        ans = 2;
                        break;
                    }
                }
            }
            pw.println(ans);
        }
        pw.flush();
    }

    private static boolean isSorted(int[] a) {
        for(int i = 1;i < a.length;i++) {
            if (a[i] < a[i - 1]) {
                return false;
            }
        }
        return true;
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