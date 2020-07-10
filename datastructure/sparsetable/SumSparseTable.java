package datastructure.sparsetable;

/**
 * an implementation of sparse table
 * @operation sum(l, r) O(k) = O(log n)
 * @operation init() O(n log n)
 *
 * sum can be simply replaced by multi etc.
 *
 * @author sqzhang
 * @year 2020
 */
public class SumSparseTable {
    private int[][] st;
    private int k;
    private int n;

    public SumSparseTable(int[] a) {
        n = a.length;
        k = (int) (Math.log(n) / Math.log(2));
        st = new int[n + 1][k + 1];
        for(int i = 0;i < n;i++) {
            st[i][0] = a[i]; // index based 0
        }
        for(int j = 1;j <= k;j++) {
            for(int i = 0;i + (1 << j) <= n;i++) {
                st[i][j] = st[i][j - 1] + st[i + (1 << (j - 1))][j - 1];
            }
        }
    }

    public int sum(int l, int r) {
        int sum = 0;
        for(int j = k;j >= 0;j--) {
            if ((1 << j) <= r - l + 1) {
                sum += st[l][j];
                l += 1 << j;
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i = 0;i <= n;i++) {
            for(int j = 0;j <= k;j++) {
                sb.append(st[i][j]).append(" ");
            }
            sb.append("\n");
        }
        sb.append("\n");
        for (int i = 0;i < n;i++) {
            sb.append(sum(0, i)).append(" ");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[] a = new int[]{1, 2, 3, 4, 5};
        SumSparseTable sparseTable = new SumSparseTable(a);
        System.out.println(sparseTable);
        System.out.println(sparseTable.sum(1, 4));
        System.out.println(sparseTable.sum(1, 5));
        System.out.println(sparseTable.sum(1, 6));
    }

}
