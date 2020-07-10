package datastructure.segmenttree;

import java.util.Arrays;

/**
 * an implementation of segment tree
 * @operation lazily range add x from a[l] to a[r] O(log n)
 * @operation get a[i] O(log n)
 *
 * @author sqzhang
 * @year 2020
 */
public class SegmentTreeRangeUpdateLazy {
    static int[] t;
    static int[] lazy;
    static int n;

    public SegmentTreeRangeUpdateLazy(int[] a) {
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
        t[p] = 0;
    }

    void push(int p) {
        if (lazy[p] > 0) {
            t[p * 2] = t[p * 2 + 1] = t[p];
            lazy[p * 2] = lazy[p * 2 + 1] = 1;
            lazy[p] = 0;
        }
    }

    /**
     * @return a[pos]
     */
    public int get(int pos) {
        return get(1, 1, n, pos);
    }

    private int get(int p, int tl, int tr, int pos) {
        if (tl == tr) {
            return t[p];
        }
        push(p);
        int tm = (tl + tr) / 2;
        if (pos <= tm) {
            return get(p * 2, tl, tm, pos);
        } else {
            return get(p * 2 + 1, tm + 1, tr, pos);
        }
    }

    /**
     * set a[l...r] = val
     */
    public void update(int l, int r, int val) {
        update(1, 1, n, l, r, val);
    }

    private void update(int p, int tl, int tr, int l, int r, int val) {
        if (l > r) {
            return;
        }
        if (l == tl && r == tr) {
            t[p] = val;
            lazy[p] = 1;
            return;
        }
        push(p);
        int tm =  (tl + tr) / 2;
        update(p * 2, tl, tm, l, Math.min(r, tm), val);
        update(p * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, val);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.toString(t)).append("\n");
        sb.append(Arrays.toString(lazy)).append("\n");
        for (int i = 1;i <= n;i++) {
            sb.append(get(i)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4, 5};
        SegmentTreeRangeUpdateLazy tree = new SegmentTreeRangeUpdateLazy(a);
        System.out.println(tree); // 1 2 3 4 5
        tree.update(2, 4, 9);
        System.out.println(tree); // 1 9 9 9 5
    }
}