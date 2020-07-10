package datastructure.fenwicktree;

import java.util.Arrays;

/**
 * @author sqzhang
 * @year 2020
 * 范围修改，单点查询，适用于多次修改的情况
 */
public class FenwickTreePointQuery {
    final int n;
    private int[] tree;

    public FenwickTreePointQuery(int[] a) {
        n = a.length + 1;
        tree = new int[n];
        for(int i = 1;i < n;i++) {
            tree[i] = i == 1 ? a[0] : a[i - 1] - a[i - 2];
        }

        for (int i = 1; i < n; i++) {
            int pi = i + lowbit(i);
            if (pi < n) {
                tree[pi] += tree[i];
            }
        }
    }

    public void rangeAdd(int l, int r, int x) {
        add(l, x);
        add(r + 1, -x);
    }

    private void add(int i, int x) {
        while (i < n) {
            tree[i] += x;
            i += lowbit(i);
        }
    }

    private int pointQuery(int i) {
        int sum = 0;
        while (i != 0) {
            sum += tree[i];
            i -= lowbit(i); // i -= lowbit(i);
        }
        return sum;
    }

    private int lowbit(int i) {
        return i & -i;
    }

    public static void main(String[] args) {
        int[] values = new int[] {1, 2, 3, 4};
        FenwickTreePointQuery pointQuery = new FenwickTreePointQuery(values);
        pointQuery.rangeAdd(1, 3, 2);
        System.out.println(Arrays.toString(pointQuery.tree));
        pointQuery.rangeAdd(3, 4, 1);
        System.out.println(Arrays.toString(pointQuery.tree));
        for (int i = 1;i <= values.length;i++) {
            System.out.print(pointQuery.pointQuery(i) + " "); // 3 4 6 5
        }
        System.out.println();
    }
}
