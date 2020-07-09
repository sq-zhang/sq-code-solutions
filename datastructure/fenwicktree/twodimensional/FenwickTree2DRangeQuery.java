package datastructure.fenwicktree.twodimensional;

/**
 *
 * @author sqzhang
 * @year 2020
 *
 * a single update && range query implementation of two-d fenwick tree
 *
 */
public class FenwickTree2DRangeQuery {
    int n;
    int[][] tree;

    public FenwickTree2DRangeQuery(int[][] values) {
        n = values.length + 1;
        tree = new int[n][n];
        for(int i = 1;i < n;i++) {
            for(int j = 1;j < n;j++) {
                tree[i][j] = values[i - 1][j - 1];
            }
        }

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

    private int lowbit(int i) {
        return i & (-i);
    }

    private int prefixSum(int x, int y) {
        int sum = 0, y0 = y;
        while(x != 0) {
            y = y0;
            while (y != 0) {
                sum += tree[x][y];
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

    public void add(int x, int y, int v) {
        int y0 = y;
        while (x < n) {
            y = y0;
            while(y < n) {
                tree[x][y] += v;
                y += lowbit(y);
            }
            x += lowbit(x);
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("tree:").append("\n");
        for (int i = 1;i < n;i++) {
            for (int j = 1;j < n;j++) {
                sb.append(tree[i][j]).append(" ");
            }
            sb.append("\n");
        }

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
        int[][] matrix = new int[][]{{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
        FenwickTree2DRangeQuery rangeQuery = new FenwickTree2DRangeQuery(matrix);
        System.out.println(rangeQuery);

        rangeQuery.add(2, 2, 2);
        System.out.println(rangeQuery);
        rangeQuery.add(2, 3, -1);
        System.out.println(rangeQuery);
    }
}
