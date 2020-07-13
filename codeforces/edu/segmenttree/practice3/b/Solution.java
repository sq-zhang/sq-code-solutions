package codeforces.edu.segmenttree.practice3.b;

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
        a = sc.nextArray(n);
        t = new int[4 * n];
        build(1, 0, n - 1);
        int[] ans = new int[n];
        for (int i = n - 1;i >= 0;i--) {
            ans[i] = find(1, 1, n, t[1] - a[i] - 1);
            update(1, 1, n, ans[i]);
        }
        for(int i = 0;i < n;i++) {
            pw.print(ans[i] + " ");
        }
        pw.println();
        pw.flush();
    }

    static void build(int p, int l, int r) {
        if (l == r) {
            t[p] = 1;
            return;
        }
        int mid = (l + r) / 2;
        build(p * 2, l, mid);
        build(p * 2 + 1, mid + 1, r);
        t[p] = t[p * 2] + t[p * 2 + 1];
    }

    static void update(int p, int tl, int tr, int pos) {
        if (tl == tr) {
            t[p] ^= 1;
            return;
        }
        int mid = (tl + tr) / 2;
        if (pos <= mid) {
            update(p * 2, tl, mid, pos);
        } else {
            update(p * 2 + 1, mid + 1, tr, pos);
        }
        t[p] = t[p * 2] + t[p * 2 + 1];
    }

    static int find(int p, int tl, int tr, int k) {
        if (tl == tr) {
            return tl;
        }
        int leftSum = t[p * 2];
        int mid = (tl + tr) / 2;
        if (k < leftSum) {
            return find(p * 2, tl, mid, k);
        } else {
            return find(p * 2 + 1, mid + 1, tr, k - leftSum);
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