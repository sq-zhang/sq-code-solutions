package atcoder.ABC174.f;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);
    static int maxN = 5 * (int)1e5;
    static BitSet[] t = new BitSet[maxN + 1];
    static int n;
    public static void main(String[] args) {
        n = sc.nextInt();
        for (int i = 0;i <= maxN;i++) {
            t[i] = new BitSet(n);
        }
        int q = sc.nextInt();
        int[] a = sc.nextArray(n);
        build(a, 1, 0, n - 1);
        while (q-- > 0) {
            int l = sc.nextInt(), r = sc.nextInt();
            pw.println(sum(1, 1, n, l, r).cardinality());
        }
        pw.flush();
    }

    static void build(int[]a, int p, int tl, int tr) {
        if (tl == tr) {
            t[p].set(a[tl]);
            return;
        }
        int tm = (tl + tr) / 2;
        build(a, p * 2, tl, tm);
        build(a, p * 2 + 1, tm + 1, tr);
        t[p].or(t[p * 2]);
        t[p].or(t[p * 2 + 1]);
    }

    static BitSet sum(int p, int tl, int tr, int l, int r) {
        if (l > r) {
            return new BitSet(n);
        }
        if (l == tl && r == tr) {
            return t[p];
        }
        int tm = (tl + tr) / 2;
        BitSet left = sum(p * 2, tl, tm, l, Math.min(r, tm));
        BitSet right = sum(p * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);
        BitSet res = new BitSet(n);
        res.or(left);
        res.or(right);
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