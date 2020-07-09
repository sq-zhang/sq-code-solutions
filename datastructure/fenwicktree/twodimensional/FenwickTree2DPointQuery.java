package datastructure.fenwicktree.twodimensional;

/**
 * @author sqzhang
 * @year 2020
 *
 * a range update && single query implementation of two-d fenwick tree
 *
 */
public class FenwickTree2DPointQuery {
    final int n;

    private int[][] tree;

    public FenwickTree2DPointQuery(int[][] a) {

        n = a.length + 1;
        tree = new int[n][n];
        for(int i = 1;i < n;i++) {
            for(int j = 1;j < n;j++) {
                tree[i][j] = a[i - 1][j - 1] - ((i == 1) ? 0 : a[i - 2][j - 1])
                    - ((j == 1) ? 0 : a[i - 1][j - 2]) + ((i == 1 || j == 1) ? 0 : a[i - 2][j - 2]);
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

    public void rangeAdd(int x1, int y1, int x2, int y2, int val) {
        add(x1, y1, val);
        add(x1, y2 + 1, -val);
        add(x2 + 1, y1, -val);
        add(x2 + 1, y2 + 1, val);
    }

    private void add(int x, int y, int v) {
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

    private int pointQuery(int x, int y) {
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

    private static int lowbit(int i) {
        return i & -i;
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

        sb.append("pointQuery:").append("\n");
        for (int i = 1;i < n;i++) {
            for (int j = 1;j < n;j++) {
                sb.append(pointQuery(i, j)).append(" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        int[][] matrix = new int[][] {{1, 4, 8}, {6, 7, 2}, {3, 9, 5}};
        FenwickTree2DPointQuery pointQuery = new FenwickTree2DPointQuery(matrix);
        System.out.println(pointQuery);

        pointQuery.rangeAdd(1, 1, 1, 2, 1);
        System.out.println(pointQuery);
    }
}
