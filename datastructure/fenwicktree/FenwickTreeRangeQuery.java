package datastructure.fenwicktree;

import java.util.Arrays;

/**
 * @author sqzhang
 * @year 2020
 */
public class FenwickTreeRangeQuery {
    int n;
    int[] tree;
    public FenwickTreeRangeQuery(int sz) {
        tree = new int[(n = sz + 1)];
    }
    public FenwickTreeRangeQuery(int[] values) {
        n = values.length + 1;
        tree = new int[n];
        for(int i = 1;i < n;i++) {
            tree[i] = values[i - 1];
        }

        for (int i = 1; i < n; i++) {
            int parent = i + lowbit(i);
            if (parent < n) {
                tree[parent] += tree[i];
            }
        }
    }

    private int lowbit(int x) {
        return x & (-x);
    }

    private int prefixSum(int i) {
        int sum = 0;
        while (i != 0) {
            sum += tree[i];
            i -= lowbit(i);
        }
        return sum;
    }

    public int sum(int left, int right) {
        return prefixSum(right) - prefixSum(left - 1);
    }

    // Set index i to be equal to v, O(log(n))
    public void set(int i, long v) {
        int origin = sum(i, i);
        v -= origin;
        while (i < n) {
            tree[i] += v;
            i += lowbit(i);
        }
    }

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 5, 6, 7, 3, 4, 5};
        FenwickTreeRangeQuery fenwickTreeRangeQuery = new FenwickTreeRangeQuery(nums);
        System.out.println(Arrays.toString(fenwickTreeRangeQuery.tree));
        for (int i = 0;i <= nums.length;i++) {
            System.out.print(fenwickTreeRangeQuery.prefixSum(i) + " ");
        }
        System.out.println();
    }
}
