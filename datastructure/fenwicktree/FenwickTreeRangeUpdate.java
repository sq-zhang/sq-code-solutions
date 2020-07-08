package datastructure.fenwicktree;

/**
 * @author sqzhang
 * @year 2020
 */
public class FenwickTreeRangeUpdate {
    final int n;

    private int[] tree;
    private int[] cur;

    public FenwickTreeRangeUpdate(int[] values) {

        n = values.length + 1;
        tree = new int[n];
        for(int i = 1;i < n;i++) {
            tree[i] = values[i - 1];
        }
        for (int i = 1; i < n; i++) {
            int parent = i + lowbit(i);
            if (parent < n) tree[parent] += tree[i];
        }

        cur = tree.clone();
    }

    // Update the interval [left, right] with the value 'val', O(log(n))
    public void updateRange(int left, int right, int val) {
        add(left, +val);
        add(right + 1, -val);
    }

    // Add 'v' to index 'i' and all the ranges responsible for 'i', O(log(n))
    private void add(int i, int v) {
        while (i < n) {
            cur[i] += v;
            i += lowbit(i);
        }
    }

    // Get the value at a specific index. The logic behind this method is the
    // same as finding the prefix sum in a Fenwick tree except that you need to
    // take the difference between the current tree and the original to get
    // the point value.
    public int prefixSum(int i) {
        return prefixSum(i, cur) - prefixSum(i - 1, tree);
    }

    // Computes the prefix sum from [1, i], O(log(n))
    private int prefixSum(int i, int[] tree) {
        int sum = 0;
        while (i != 0) {
            sum += tree[i];
            i &= ~lowbit(i); // i -= lsb(i);
        }
        return sum;
    }

    private static int lowbit(int i) {
        return i & -i;
    }
}
