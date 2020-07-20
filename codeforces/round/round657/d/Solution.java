package codeforces.round.round657.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static long[] t;
    public static void main(String[] args) {
        int n = sc.nextInt(), h = sc.nextInt(),
            m = sc.nextInt(), k = sc.nextInt();
        t = new long[4 * m];
        ArrayList<Integer> startTimes = new ArrayList<>();
        for (int i = 0; i < n;i++) {
            int hi = sc.nextInt(), mi = sc.nextInt() % (m / 2);
            update(0, 0, m, mi);
            update(0, 0, m, mi + m / 2);
            startTimes.add(mi);
        }

        long bestAns = Integer.MAX_VALUE;
        long bestTime = 0;
        for (int i:startTimes) {
            long toCancel = sum(0, 0, m, i + 1, i + k - 1);
            if (toCancel < bestAns) {
                bestAns = toCancel;
                bestTime = i;
            }
        }
        pw.println(bestAns + " " + (bestTime + k) % (m / 2));
        for (int i = 0;i < startTimes.size(); i++) {
            int t = startTimes.get(i);
            if (t > bestTime && t < bestTime + k) {
                pw.print((i + 1) + " ");
            }
            if (t + m / 2 > bestTime && t + m / 2 < bestTime + k) {
                pw.print((i + 1) + " ");
            }
        }
        pw.println();
        pw.close();
    }

    static long sum(int p, int tl, int tr, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (l == tl && r == tr) {
            return t[p];
        }
        int tm = (tl + tr) / 2;
        long left = sum(p * 2 + 1, tl, tm, l, Math.min(r, tm));
        long right = sum(p * 2 + 2, tm + 1, tr, Math.max(l, tm + 1), r);
        return left + right;
    }

    static void update(int p, int tl, int tr, int pos) {
        if (tl == tr) {
            t[p]++;
            return;
        }
        int tm =  (tl + tr) / 2;
        if (pos <= tm) {
            update(p * 2 + 1, tl, tm, pos);
        } else {
            update(p * 2 + 2, tm + 1, tr, pos);
        }
        t[p] = t[p * 2 + 1] + t[p * 2 + 2];
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