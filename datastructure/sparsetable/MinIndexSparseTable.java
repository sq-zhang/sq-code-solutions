package datastructure.sparsetable;

/**
 * an implementation of sparse table
 * @operation min(l, r) O(1)
 * @operation minIndex(l, r) O(1)
 * @operation init() O(n log n)
 *
 * Math.min can be simply replaced by Math.max.
 *
 * @author sqzhang
 * @year 2020
 */
public class MinIndexSparseTable {
    private int[][] st;
    private int[][] idx;
    private int[] log;
    private int k;
    private int n;

    public MinIndexSparseTable(int[] a) {
        n = a.length;
        log = new int[n + 1];
        for(int i = 2;i <= n;i++) {
            log[i] = log[i / 2] + 1;
        }
        k = (int) (Math.log(n) / Math.log(2));
        st = new int[n + 1][k + 1];
        idx = new int[n + 1][k + 1];

        for(int i = 0;i < n;i++) {
            st[i][0] = a[i]; // index based 0
            idx[i][0] = i;
        }
        for(int j = 1;j <= k;j++) {
            for(int i = 0;i + (1 << j) <= n;i++) {
                int left = st[i][j - 1], right = st[i + (1 << (j - 1))][j - 1];
                if (left >= right) {
                    st[i][j] = right;
                    idx[i][j] = idx[i + (1 << (j - 1))][j - 1];
                } else {
                    st[i][j] = left;
                    idx[i][j] = idx[i][j - 1];
                }
            }
        }
    }

    public int min(int l, int r) {
        int j = log[r - l + 1];
        return Math.min(st[l][j], st[r - (1 << j) + 1][j]);
    }

    public int minIndex(int l, int r) {
        int len = r - l + 1;
        int j = log[len];
        long left = st[l][j], right = st[r - (1 << j) + 1][j];
        if (left <= right) {
            return idx[l][j];
        } else {
            return idx[r - (1 << j) + 1][j];
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i < n;i++) {
            for(int j = 0;j <= k;j++) {
                sb.append(st[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        for (int i = 0;i < n;i++) {
            sb.append(minIndex(0, i)).append(" ");
        }
        sb.append("\n");
        for (int i = 0;i < n;i++) {
            sb.append(min(0, i)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        MinIndexSparseTable sparseTable = new MinIndexSparseTable(a);
        System.out.println(sparseTable);
        System.out.println(sparseTable.min(1, 4));
        System.out.println(sparseTable.minIndex(1, 4));
    }

}
