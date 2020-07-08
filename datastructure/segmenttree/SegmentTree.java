package datastructure.segmenttree;

import java.util.function.BinaryOperator;

/**
 * @author sqzhang
 * @year 2020
 */
public class SegmentTree {

    private int n;
    private long[] tree;
    private BinaryOperator<Long> fn;
    private static final BinaryOperator<Long> DEFAULT_FN = Long::sum;
    private static final int ROOT_INDEX = 0;
    public SegmentTree(long[] values) {
        this(values, DEFAULT_FN);
    }

    public SegmentTree(long[] values, BinaryOperator<Long> fn) {
        n = values.length;
        int N = 4 * n;
        tree = new long[N];
        this.fn = fn;
        buildSegmentTree(ROOT_INDEX, 0, n - 1, values);
    }

    private void buildSegmentTree(int i, int left, int right, long[] values) {
        if (left == right) {
            tree[i] = values[left];
            return;
        }
        int mid = (left + right) / 2;
        int leftChild = 2 * i + 1, rightChild = 2 * (i + 1);
        buildSegmentTree(leftChild, left, mid, values);
        buildSegmentTree(rightChild, mid + 1, right, values);
        tree[i] = fn.apply(tree[leftChild], tree[rightChild]);
    }

    public long rangeQuery(int queryLeft, int queryRight) {
        return rangeQuery(0, 0, n - 1, queryLeft, queryRight);
    }

    private long rangeQuery(int i, int left, int right, int queryLeft, int queryRight) {
        if (left == queryLeft && right == queryRight) {
            return tree[i];
        }
        int mid = (left + right) / 2;
        int leftChild = 2 * i + 1, rightChild = 2 * (i + 1);
        if (queryLeft <= mid && queryRight > mid) {
            long leftResult = rangeQuery(leftChild, left, mid, queryLeft, mid);
            long rightResult = rangeQuery(rightChild, mid + 1, right, mid + 1, queryRight);
            return fn.apply(leftResult, rightResult);
        } else if (queryLeft <= mid) {
            return rangeQuery(leftChild, left, mid, queryLeft, queryRight);
        } else {
            return rangeQuery(rightChild, mid + 1, right, queryLeft, queryRight);
        }
    }

    public void update(int i, long newValue) {
        update(0, i, 0, n - 1, newValue);
    }

    private void update(int i, int pos, int left, int right, long newValue) {
        if (left == pos && right == pos) {
            tree[i] = newValue;
            return;
        }
        int mid = (left + right) / 2;
        int leftChild = 2 * i + 1, rightChild = 2 * (i + 1);
        if (pos <= mid) {
            update(leftChild, pos, left, mid, newValue);
        } else {
            update(rightChild, pos, mid + 1, right, newValue);
        }

        tree[i] = fn.apply(tree[leftChild], tree[rightChild]);
    }

    public static void main(String[] args) {
        long[] values = {3, 1, 2, 1};

        SegmentTree sumSegmentTree = new SegmentTree(values);
        for(int l = 0;l < values.length;l++) {
            for(int r = l;r < values.length;r++) {
                System.out.printf("sum[%d, %d] = %d\n", l, r, sumSegmentTree.rangeQuery(l, r));
            }
        }

        System.out.println("After Update v[1] = 4");
        sumSegmentTree.update(1, 4);
        for(int l = 0;l < values.length;l++) {
            for(int r = l;r < values.length;r++) {
                System.out.printf("sum[%d, %d] = %d\n", l, r, sumSegmentTree.rangeQuery(l, r));
            }
        }
    }
}