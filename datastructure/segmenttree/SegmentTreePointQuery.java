package datastructure.segmenttree;

import java.util.Arrays;

/**
 * an implementation of segment tree
 * @operation range add x from a[l] to a[r] O(log n)
 * @operation get a[i] O(log n)
 *
 * @author sqzhang
 * @year 2020
 */
public class SegmentTreePointQuery {
    static int[] t;
    static int n;

    public SegmentTreePointQuery(int[] a) {
        n = a.length;
        t = new int[4 * n];
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
        int tm = (tl + tr) / 2;
        if (pos <= tm) {
            return t[p] + get(p * 2, tl, tm, pos);
        } else {
            return t[p] + get(p * 2 + 1, tm + 1, tr, pos);
        }
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
            return;
        }
        int tm =  (tl + tr) / 2;
        update(p * 2, tl, tm, l, Math.min(r, tm), val);
        update(p * 2 + 1, tm + 1, tr, Math.max(l, tm + 1), r, val);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.toString(t)).append("\n");
        for (int i = 1;i <= n;i++) {
            sb.append(get(i)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] a = new int[] {1, 2, 3, 4, 5};
        SegmentTreePointQuery tree = new SegmentTreePointQuery(a);
        System.out.println(tree); // 1 2 3 4 5
        tree.update(2, 3, 3);
        System.out.println(tree); // 1 5 6 6 6
    }
}