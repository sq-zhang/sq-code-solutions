package datastructure.segmenttree;

import java.util.Arrays;

/**
 * an implementation of segment tree
 * @operation range add x from a[l] to a[r] O(log n)
 * @operation range sum(l, r)
 *
 * @author sqzhang
 * @year 2020
 */
public class SegmentTreeRangeAddLazy {
    private int[] t;
    private int[] lazy;
    private int n;

    public SegmentTreeRangeAddLazy(int[] a) {
        n = a.length;
        t = new int[4 * n];
        lazy = new int[4 * n];
        build(a, 1, 0, n - 1);
    }

    private void build(int[] a, int p, int tl, int tr) {
        if (tl == tr) {
            t[p] = a[tl];
            return;
        }
        int tm = (tl + tr) / 2;
        build(a, p * 2, tl, tm);
        build(a, p * 2 + 1, tm + 1, tr);
        t[p] = t[p * 2] + t[p * 2 + 1];
    }

    private void push(int p, int tl, int tr) {
        if (lazy[p] > 0) {
            t[p] += lazy[p];
            if (tl != tr) {
                lazy[p * 2] += lazy[p];
                lazy[p * 2 + 1] += lazy[p];
            }
            lazy[p] = 0;
        }
    }

    private int sum(int p, int tl, int tr, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (l <= tl && tr <= r) {
            return t[p];
        }
        push(p, tl, tr);
        int tm = (tl + tr) / 2;
        int left = sum(p * 2, tl, tm, l, Math.min(r, tm));
        int right = sum(p * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);
        return left + right;
    }

    private void update(int p, int tl, int tr, int l, int r, int val) {
        if (l > r) {
            return;
        }
        push(p, tl, tr);
        if (l == tl && r == tr) {
            t[p] += val;
            lazy[p] += val;
            return;
        }
        int tm =  (tl + tr) / 2;
        update(p * 2, tl, tm, l, Math.min(r, tm), val);
        update(p * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, val);
        t[p] = t[p * 2] + t[p * 2 + 1];
    }

    /**
     * @return get range sum from l to r
     */
    public int sum(int l, int r) {
        return sum(1, 1, n, l, r);
    }
    /**
     * add val to element from l to r
     */
    public void update(int l, int r, int val) {
        update(1, 1, n, l, r, val);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.toString(t)).append("\n");
        sb.append(Arrays.toString(lazy)).append("\n");
        for (int i = 1;i <= n;i++) {
            sb.append(sum(1, i)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4, 5};
        SegmentTreeRangeAddLazy tree = new SegmentTreeRangeAddLazy(a);
        System.out.println(tree); // 1 3 6 10 15
        tree.update(2, 3, 3); // 1 5 6 4 5
        System.out.println(tree); // 1 6 12 16 21
        System.out.println(tree.sum(3, 5)); // 15
    }
}