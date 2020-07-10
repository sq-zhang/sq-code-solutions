package datastructure.segmenttree;

import java.util.Arrays;

/**
 * @author sqzhang
 * @year 2020
 */
public class SegmentTreeFlipCoin {

    private int[] t;
    private int[] lazy;
    private int n;

    public SegmentTreeFlipCoin(int n) {
        this.n = n;
        t = new int[4 * n];
        lazy = new int[4 * n];
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
        if (tl > r || tr < l) {
            return 0;
        }
        push(p, tl, tr);
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
        SegmentTreeFlipCoin tree = new SegmentTreeFlipCoin(4);
        System.out.println(tree); // 0 0 0 0

        tree.update(1, 3); // 1 1 1 0
        System.out.println(tree); // 1 2 3 3

        tree.update(2, 4); // 1 0 0 1
        System.out.println(tree); // 1 1 1 2
    }
}
