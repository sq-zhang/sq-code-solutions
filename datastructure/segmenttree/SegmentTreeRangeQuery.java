package datastructure.segmenttree;

import java.util.Arrays;

/**
 * an implementation of segment tree
 * @operation get sum between a[l] and a[r] O(log n)
 * @operation set a[p] = v O(log n)
 *
 * @author sqzhang
 * @year 2020
 */
public class SegmentTreeRangeQuery {
    static int[] t;
    static int n;

    public SegmentTreeRangeQuery(int[] a) {
        n = a.length;
        t = new int[4 * n];
        build(a, 1, 0, n - 1);
    }

    /**
     * build segment tree
     */
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

    /**
     * @return sum of [l, r]
     */
    public int sum(int l, int r) {
        return sum(1, 1, n, l, r);
    }

    private int sum(int p, int tl, int tr, int l, int r) {
        if (l > r) {
            return 0;
        }
        if (l == tl && r == tr) {
            return t[p];
        }
        int tm = (tl + tr) /2;
        int left = sum(p * 2, tl, tm, l, Math.min(r, tm));
        int right = sum(p * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r);
        return left + right;
    }

    /**
     * set a[pos] = val
     */
    public void update(int pos, int val) {
        update(1, 1, n, pos, val);
    }

    private void update(int p, int tl, int tr, int pos, int val) {
        if (tl == tr) {
            t[p] = val;
            return;
        }
        int tm =  (tl + tr) / 2;
        if (pos <= tm) {
            update(p * 2, tl, tm, pos, val);
        } else {
            update(p * 2 + 1, tm + 1, tr, pos, val);
        }
        t[p] = t[p * 2] + t[p * 2 + 1];
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.toString(t)).append("\n");
        for (int i = 1;i <= n;i++) {
            sb.append(sum(1, i)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4, 5};
        SegmentTreeRangeQuery tree = new SegmentTreeRangeQuery(a);
        System.out.println(tree);
        tree.update(2, -2);
        System.out.println(tree);
    }
}