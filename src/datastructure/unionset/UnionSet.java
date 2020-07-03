package datastructure.unionset;

/**
 * an implementation of Union Set
 * @author sqzhang
 * @year 2020
 */
public class UnionSet {
    private int[] root;
    private int[] weights;

    public UnionSet(int size) {
        if (size < 0) {
            throw new IllegalArgumentException();
        }
        root = new int[size];
        weights = new int[size];
        for (int i = 0; i < size; i++) {
            root[i] = -1;
            weights[i] = 1;
        }
    }

    public int find(int x) {
        while (root[x] >= 0) {
            x = root[x];
        }
        return x;
    }

    public void union(int x, int y) {
        if (x == y)
            return;
        if (weights[x] > weights[y]) {
            int temp = x;
            x = y;
            y = temp;
        }
        weights[y] += weights[x];
        root[x] = y;
    }

    public int getSetWeight(int x) {
        return weights[x];
    }

    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }
}
