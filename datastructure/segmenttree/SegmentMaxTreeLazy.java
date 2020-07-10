package datastructure.segmenttree;

import java.util.Arrays;

/**
 * an implementation of segment tree
 * @operation range add x from a[l] to a[r] O(log n)
 * @operation get max(l, r)
 *
 * @author sqzhang
 * @year 2020
 */
public class SegmentMaxTreeLazy {
    static int[] t;
    static int[] lazy;
    static int n;

    public SegmentMaxTreeLazy(int[] a) {
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
        t[p] = Math.max(t[p * 2], t[p * 2 + 1]);
    }

    void push(int p) {
        t[p * 2] += lazy[p];
        lazy[p * 2] += lazy[p];
        t[p * 2 + 1] += lazy[p];
        lazy[p * 2 + 1] += lazy[p];
        lazy[p] = 0;
    }

    /**
     * @return query maximum from l to r
     */
    public int query(int l, int r) {
        return query(1, 1, n, l, r);
    }

    private int query(int p, int tl, int tr, int l, int r) {
        if (l > r) {
            return Integer.MIN_VALUE;
        }
        if (l <= tl && tr <= r) {
            return t[p];
        }
        push(p);
        int tm = (tl + tr) / 2;
        int left = query(p * 2, tl, tm, l, Math.min(r, tm));
        int right = query(p * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);
        return Math.max(left, right);
    }

    /**
     * add val to element from l to r
     */
    public void update(int l, int r, int val) {
        update(1, 1, n, l, r, val);
    }

    private void update(int p, int tl, int tr, int l, int r, int val) {
        if (l > r) {
            return;
        }
        if (l == tl && r == tr) {
            t[p] += val;
            lazy[p] += val;
            return;
        }
        push(p);
        int tm =  (tl + tr) / 2;
        update(p * 2, tl, tm, l, Math.min(r, tm), val);
        update(p * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, val);
        t[p] = Math.max(t[p * 2], t[p * 2 + 1]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.toString(t)).append("\n");
        sb.append(Arrays.toString(lazy)).append("\n");
        for (int i = 1;i <= n;i++) {
            sb.append(query(1, i)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4, 5};
        SegmentMaxTreeLazy tree = new SegmentMaxTreeLazy(a);
        System.out.println(tree); // 1 2 3 4 5
        tree.update(2, 3, 3);
        System.out.println(tree); // 1 5 6 6 6
    }
}