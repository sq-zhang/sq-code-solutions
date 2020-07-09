package datastructure.fenwicktree;

import java.util.Arrays;

/**
 * @author sqzhang
 * @year 2020
 */
public class FenwickTreeRangeUpdate {
    int n;
    int[] tree1, tree2;

    public FenwickTreeRangeUpdate(int[] a) {
        n = a.length + 1;
        tree1 = new int[n];
        tree2 = new int[n];
        for(int i = 1;i < n;i++) {
            tree1[i] = i == 1 ? a[0] : a[i - 1] - a[i - 2];
            tree2[i] = i * tree1[i];
        }

        for (int i = 1; i < n; i++) {
            int pi = i + lowbit(i);
            if (pi < n) {
                tree1[pi] += tree1[i];
                tree2[pi] += tree2[i];
            }
        }
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    private int prefixSum(int p) {
        int sum = 0, i = p;
        while (i != 0) {
            sum += tree1[i] * (p + 1) - tree2[i];
            i -= lowbit(i);
        }
        return sum;
    }

    public int sum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    public void add(int p, int x) {
        int i = p;
        while (i < n) {
            tree1[i] += x;
            tree2[i] += x * p;
            i += lowbit(i);
        }
    }

    public void rangeAdd(int l, int r, int x) {
        add(l, x);
        add(r + 1, -x);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Arrays.toString(tree1)).append("\n")
            .append(Arrays.toString(tree2)).append("\n");
        for (int i = 1;i < n;i++) {
            sb.append(prefixSum(i)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3, 4};
        FenwickTreeRangeUpdate rangeUpdate = new FenwickTreeRangeUpdate(nums);
        System.out.println(rangeUpdate);

        rangeUpdate.rangeAdd(1, 2, 1);
        System.out.println(rangeUpdate); // 2 5 8 12
        rangeUpdate.rangeAdd(1, 3, -2);
        System.out.println(rangeUpdate); // 0, 1, 2, 6
    }
}
