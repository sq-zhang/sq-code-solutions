package datastructure.sparsetable;

/**
 * an implementation of sparse table
 * @operation max(l, r) O(1)
 * @operation init() O(n log n)
 *
 * Math.max can be simply replaced by Math.min, gcd etc.
 *
 * @author sqzhang
 * @year 2020
 */
public class MaxSparseTable {
    private int[][] st;
    private int[] log;
    private int k;
    private int n;

    public MaxSparseTable(int[] a) {
        n = a.length;
        log = new int[n + 1];
        for(int i = 2;i <= n;i++) {
            log[i] = log[i / 2] + 1;
        }
        k = (int) (Math.log(n) / Math.log(2));
        st = new int[n + 1][k + 1];

        for(int i = 0;i < n;i++) {
            st[i][0] = a[i]; // index based 0
        }
        for(int j = 1;j <= k;j++) {
            for(int i = 0;i + (1 << j) <= n;i++) {
                st[i][j] = Math.max(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
    }

    public int max(int l, int r) {
        int j = log[r - l + 1];
        return Math.max(st[l][j], st[r - (1 << j) + 1][j]);
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
            sb.append(max(0, i)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        MaxSparseTable sparseTable = new MaxSparseTable(a);
        System.out.println(sparseTable);
        System.out.println(sparseTable.max(1, 4));
    }

}
