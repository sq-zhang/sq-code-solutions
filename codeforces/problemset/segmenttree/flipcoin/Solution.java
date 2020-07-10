package codeforces.problemset.segmenttree.flipcoin;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://www.codechef.com/problems/FLIPCOIN
public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt(), q = sc.nextInt();
        SegmentTreeFlipCoin flipCoin = new SegmentTreeFlipCoin(n);
        while (q-- > 0) {
            int type = sc.nextInt(), l = sc.nextInt() + 1, r = sc.nextInt() + 1;
            if (type == 0) {
                flipCoin.update(l, r);
            } else {
                pw.println(flipCoin.query(l, r));
            }
        }
        pw.flush();
    }

    public static class SegmentTreeFlipCoin {
        private int[] t;
        private int[] lazy;
        private int n;

        public SegmentTreeFlipCoin(int n) {
            this.n = n;
            t = new int[4 * n + 4];
            lazy = new int[4 * n + 4];
        }

        private void push(int p, int tl, int tr) {
            if (lazy[p] > 0) {
                t[p] = (tr - tl + 1) - t[p];
                if (tl != tr) {
                    lazy[2 * p] ^= 1;
                    lazy[2 * p + 1] ^= 1;
                }
                lazy[p] = 0;
            }
        }

        private int query(int p, int tl, int tr, int l, int r) {
            push(p, tl, tr);
            if (tl > r || tr < l) {
                return 0;
            }
            if (l <= tl && tr <= r) {
                return t[p];
            }
            int mid = (tl + tr) / 2;
            int left = query(p * 2, tl, mid, l, r);
            int right = query(p * 2 + 1, mid + 1, tr, l, r);
            return left + right;
        }

        private void update(int p, int tl, int tr, int l, int r) {
            push(p, tl, tr);
            if (tl > r || tr < l) {
                return;
            }
            // push(p, tl, tr); // why push here results wrong answer??
            if (l <= tl && tr <= r) {
                t[p] = (tr - tl + 1) - t[p];
                if (tl != tr) {
                    lazy[2 * p] ^= 1;
                    lazy[2 * p + 1] ^= 1;
                }
                return;
            }
            int mid = (tr + tl) / 2;
            update(p * 2, tl, mid, l, r);
            update(p * 2 + 1, mid + 1, tr, l, r);
            t[p] = t[2 * p] + t[2 * p + 1];
        }

        /**
         * @return get range sum from l to r
         */
        public int query(int l, int r) {
            return query(1, 1, n, l, r);
        }
        /**
         * add val to element from l to r
         */
        public void update(int l, int r) {
            update(1, 1, n, l, r);
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