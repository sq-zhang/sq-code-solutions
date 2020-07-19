package codeforces.edu.segmenttree.practice3.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static long[] t;
    public static void main(String[] args) {
        int n = sc.nextInt(), m = sc.nextInt();
        t = new long[4 * n];
        while (m-- > 0) {
            int type = sc.nextInt();
            if (type == 1) {
                int l = sc.nextInt() + 1, r = sc.nextInt(), v = sc.nextInt();
                update(1, 1, n, l, r, v);
            } else {
                int pos = sc.nextInt() + 1;
                pw.println(get(1, 1, n, pos));
            }
        }
        pw.flush();
    }

    static long get(int p, int tl, int tr, int pos) {
        if (tl == tr) {
            return t[p];
        }
        int tm = (tl + tr) / 2;
        if (pos <= tm) {
            return t[p] + get(p * 2, tl, tm, pos);
        } else {
            return t[p] + get(p * 2 + 1, tm + 1, tr, pos);
        }
    }

    static void update(int p, int tl, int tr, int l, int r, int val) {
        if (l > r) {
            return;
        }
        if (l == tl && r == tr) {
            t[p] += val;
            return;
        }
        int tm =  (tl + tr) / 2;
        update(p * 2, tl, tm, l, Math.min(r, tm), val);
        update(p * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, val);
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