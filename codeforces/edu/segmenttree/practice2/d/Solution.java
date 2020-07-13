package codeforces.edu.segmenttree.practice2.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static int[] t, a;
    public static void main(String[] args) {
        int n = sc.nextInt(), m = sc.nextInt();
        a = sc.nextArray(n);
        t = new int[4 * n];
        build(1, 0, n - 1);
        while (m-- > 0) {
            int type = sc.nextInt();
            if (type == 1) {
                int i = sc.nextInt() + 1, val = sc.nextInt();
                update(1, 1, n, i, val);
            } else {
                int x = sc.nextInt(), l = sc.nextInt();
                pw.println(query(1, 1, n, l, x));
            }
        }
        pw.flush();
    }

    static void build(int p, int l, int r) {
        if (l == r) {
            t[p] = a[l];
            return;
        }
        int mid = (l + r) / 2;
        build(p * 2, l, mid);
        build(p * 2 + 1, mid + 1, r);
        t[p] = Math.max(t[p * 2], t[p * 2 + 1]);
    }

    static void update(int p, int tl, int tr, int pos, int val) {
        if (tl == tr) {
            t[p] = val;
            return;
        }
        int mid = (tl + tr) / 2;
        if (pos <= mid) {
            update(p * 2, tl, mid, pos, val);
        } else {
            update(p * 2 + 1, mid + 1, tr, pos, val);
        }
        t[p] = Math.max(t[p * 2], t[p * 2 + 1]);
    }

    static int query(int p, int tl, int tr, int l, int x) {
        if (t[p] < x) {
            return -1;
        }
        if (tr <= l) {
            return -1;
        }
        if (tl == tr) {
            return tl - 1;
        }
        int mid = (tl + tr) / 2;
        int res = query(p * 2, tl, mid, l, x);
        if (res == -1) {
            res = query(p * 2 + 1, mid + 1, tr, l, x);
        }
        return res;
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