package datastructure.fenwicktree.twodimensional;

/**
 * a range update && range query implementation of two-d fenwick tree
 * @author sqzhang
 * @year 2020
 */
public class FenwickTree2DRangeUpdate {
    final int n;

    private int[][] t1, t2, t3, t4;

    public FenwickTree2DRangeUpdate(int[][] a) {
        n = a.length + 1;
        t1 = new int[n][n];
        t2 = new int[n][n];
        t3 = new int[n][n];
        t4 = new int[n][n];

        for(int i = 1;i < n;i++) {
            for(int j = 1;j < n;j++) {
                t1[i][j] = a[i - 1][j - 1] - ((i == 1) ? 0 : a[i - 2][j - 1])
                    - ((j == 1) ? 0 : a[i - 1][j - 2]) + ((i == 1 || j == 1) ? 0 : a[i - 2][j - 2]);
                t2[i][j] = t1[i][j] * i;
                t3[i][j] = t1[i][j] * j;
                t4[i][j] = t1[i][j] * i * j;
            }
        }

        initTree(t1);
        initTree(t2);
        initTree(t3);
        initTree(t4);
    }

    private void initTree(int[][] tree) {
        for (int i = 1; i < n; i++) {
            int pi = i + lowbit(i);
            if (pi < n) {
                for(int j = 1;j < n;j++) {
                    tree[pi][j] += tree[i][j];
                }
            }
        }

        for (int i = 1; i < n; i++) {
            int pi = i + lowbit(i);
            if (pi < n) {
                for(int j = 1;j < n;j++) {
                    tree[j][pi] += tree[j][i];
                }
            }
        }
    }

    private void add(int x, int y, int v) {
        int x0 = x, y0 = y;
        while (x < n) {
            y = y0;
            while(y < n) {
                t1[x][y] += v;
                t2[x][y] += v * x0;
                t3[x][y] += v * y0;
                t4[x][y] += v * x0 * y0;
                y += lowbit(y);
            }
            x += lowbit(x);
        }
    }

    public void rangeAdd(int x1, int y1, int x2, int y2, int val) {
        add(x1, y1, val);
        add(x1, y2 + 1, -val);
        add(x2 + 1, y1, -val);
        add(x2 + 1, y2 + 1, val);
    }

    private int prefixSum(int x, int y) {
        int sum = 0, x0 = x + 1, y0 = y + 1;
        while(x != 0) {
            y = y0 - 1;
            while (y != 0) {
                sum += x0 * y0 * t1[x][y] - y0 * t2[x][y]
                        - x0 * t3[x][y] + t4[x][y];
                y -= lowbit(y);
            }
            x -= lowbit(x);
        }

        return sum;
    }

    public int sum(int x01, int y01, int x02, int y02) {
        int x1 = Math.min(x01, x02) - 1, y1 = Math.min(y01, y02) - 1;
        int x2 = Math.max(x01, x02), y2 = Math.max(y01, y02);

        return prefixSum(x2, y2) - prefixSum(x1, y2) - prefixSum(x2, y1) + prefixSum(x1, y1);
    }

    private static int lowbit(int i) {
        return i & -i;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("prefixSum:").append("\n");
        for (int i = 1;i < n;i++) {
            for (int j = 1;j < n;j++) {
                sb.append(prefixSum(i, j)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};

        FenwickTree2DRangeUpdate rangeUpdate = new FenwickTree2DRangeUpdate(matrix);
        System.out.println(rangeUpdate);

        rangeUpdate.rangeAdd(1, 1, 1, 2, 1);
        System.out.println(rangeUpdate);
    }
}
