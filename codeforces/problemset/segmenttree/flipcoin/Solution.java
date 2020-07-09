package codeforces.problemset.segmenttree.flipcoin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.codechef.com/problems/FLIPCOIN
public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);
    static int[] tree;
    static boolean[] lazy;

    public static void main(String[] args) {
        int n = sc.nextInt(), q = sc.nextInt();
        tree = new int[4 * n + 4];
        lazy = new boolean[4 * n + 4];
        while (q-- > 0) {
            int type = sc.nextInt(), l = sc.nextInt() + 1, r = sc.nextInt() + 1;
//            pw.println(type + "," + l + "," + r);
            if (type == 0) {
                update(1, n, l, r, 1);
            } else {
                pw.println(query(1, n, l, r, 1));
            }
//            print();
        }
        pw.flush();
    }

    public static void print(){
        for(int i = 1;i <= 7;i++){
            pw.print(tree[i]+ " ");
        }
        pw.println();
        for(int i = 1;i <= 7;i++){
            pw.print((lazy[i] ? "T" : "F") + " ");
        }
        pw.println();
    }

    public static int query(int s, int e, int l, int r, int p) {
        if (lazy[p]) {
            tree[p] = (e - s + 1) - tree[p];
            if (s != e) {
                lazy[2 * p] = !lazy[2 * p];
                lazy[2 * p + 1] = !lazy[2 * p + 1];
            }
            lazy[p] = false;
        }
        if (s > r || e < l) {
            return 0;
        }
        if (s >= l && e <= r) {
            return tree[p];
        }
        int mid = (s + e) / 2;
        int left = query(s, mid, l, r, 2 * p);
        int right = query(mid + 1, e, l, r, 2 * p + 1);
        return left + right;
    }

    public static void update(int s, int e, int l, int r, int p) {
        if (lazy[p]) {
            tree[p] = (e - s + 1) - tree[p];
            if (s != e) {
                lazy[2 * p] = !lazy[2 * p];
                lazy[2 * p + 1] = !lazy[2 * p + 1];
            }
            lazy[p] = false;
        }
        if (s > r || e < l) {
            return;
        }
        if (s >= l && e <= r) {
            tree[p] = (e - s + 1) - tree[p];
            if (s != e) {
                lazy[2 * p] = !lazy[2 * p];
                lazy[2 * p + 1] = !lazy[2 * p + 1];
            }
            return;
        }
        int mid = (e + s) / 2;
        update(s, mid, l, r, 2 * p);
        update(mid + 1, e, l, r, 2 * p + 1);
        tree[p] = tree[2 * p] + tree[2 * p + 1];
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