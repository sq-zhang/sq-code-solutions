package codeforces.edu.segmenttree.practice3.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static int[] t, a;
    public static void main(String[] args) {
        int n = sc.nextInt();
        int m = 2 * n;
        t = new int[4 * m];
        a = sc.nextArray(m);
        int[] idx = new int[n + 1];
        int[] ans = new int[n + 1];
        for(int i = 0;i < m;i++) {
            if (idx[a[i]] == 0) {
                idx[a[i]] = i + 1;
            } else {
                ans[a[i]] = sum(1, 1, m, idx[a[i]], i);
                update(1, 1, m, idx[a[i]]);
            }
        }
        for (int i = 1;i <= n;i++) {
            pw.print(ans[i] + " ");
        }
        pw.println();
        pw.flush();
    }

    static void update(int p, int tl, int tr, int pos) {
        if (tl == tr) {
            t[p] ^= 1;
            return;
        }
        int mid = (tl + tr) / 2;
        if (mid >= pos) {
            update(p * 2, tl, mid, pos);
        } else {
            update(p * 2 + 1, mid + 1, tr, pos);
        }
        t[p] = t[p * 2] + t[p * 2 + 1];
    }

    static int sum(int p, int tl, int tr, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (l == tl && r == tr) {
            return t[p];
        }
        int tm = (tl + tr) /2;
        int left = sum(p * 2, tl, tm, l, Math.min(r, tm));
        int right = sum(p * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);
        return left + right;
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