package codeforces.edu.segmenttree.practice1.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static int[][] t;
    static int[] a;
    public static void main(String[] args) {
        int n = sc.nextInt(), m = sc.nextInt();
        t = new int[4 * n][2];
        a = sc.nextArray(n);
        build(1, 0, n - 1);
        while (m-- > 0) {
            int type = sc.nextInt(), l = sc.nextInt() + 1, r = sc.nextInt();
            if (type == 1) {
                update(1, 1, n, l, r);
            } else {
                int[] q = min(1, 1, n, l, r);
                pw.println(q[0] + " " + q[1]);
            }
        }
        pw.flush();
    }

    static void build(int p, int tl, int tr) {
        if (tl == tr) {
            t[p] = new int[]{a[tl], 1};
            return;
        }
        int tm = (tl + tr) / 2;
        build(p * 2, tl, tm);
        build(p * 2 + 1, tm + 1, tr);
        t[p] = combine(t[p * 2], t[p * 2 + 1]);
    }

    static int[] combine(int[] l, int[] r) {
        if (l[0] < r[0]) {
            return l;
        } else if (l[0] > r[0]) {
            return r;
        } else {
            return new int[]{l[0], l[1] + r[1]};
        }
    }

    static void update(int p, int tl, int tr, int pos, int val) {
        if (tl == tr) {
            t[p] = new int[]{val, 1};;
            return;
        }
        int mid = (tl + tr) / 2;
        if (pos <= mid) {
            update(p * 2, tl, mid, pos, val);
        } else {
            update(p * 2 + 1, mid + 1, tr, pos, val);
        }
        t[p] = combine(t[p * 2], t[p * 2 + 1]);
    }

    static int[] min(int p, int tl, int tr, int l, int r) {
        if (l > r) {
            return new int[]{Integer.MAX_VALUE, 1};
        }
        if (l == tl && r == tr) {
            return t[p];
        }
        int tm = (tl + tr) /2;
        int[] left = min(p * 2, tl, tm, l, Math.min(r, tm));
        int[] right = min(p * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);
        return combine(left, right);
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