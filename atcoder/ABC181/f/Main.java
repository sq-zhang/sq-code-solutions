package atcoder.ABC181.f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.UncheckedIOException;
import java.io.Writer;
import java.nio.ByteBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] x = new int[n], y = new int[n];
        for (int i = 0;i < n;i++) {
            x[i] = sc.nextInt();
            y[i] = sc.nextInt();
        }
        int left = 1, right = 200 * 200 + 1;
        while (right - left > 1) {
            int mid = (left + right) / 2;
            IntUnionFind uf = new IntUnionFind(n + 2);
            for (int i = 0;i < n;i++) {
                int y0 = y[i] + 100, y1 = y[i] - 100;
                if (y0 * y0 < mid) {
                    uf.union(i, n);
                }
                if (y1 * y1 < mid) {
                    uf.union(i, n + 1);
                }
                for (int j = 0;j < i;j++) {
                    int dx = x[i] - x[j], dy = y[i] - y[j];
                    if (dx * dx + dy * dy < mid) {
                        uf.union(i, j);
                    }
                }
            }
            if (uf.find(n) == uf.find(n + 1)) {
                right = mid;
            } else {
                left = mid;
            }
        }
        pw.println(Math.sqrt(left) * 0.5);
        pw.flush();
    }

    static final class IntUnionFind {

        private int groups;
        private final int[] nodes;
        private final int[] rank;

        public IntUnionFind(int n) {
            groups = n;
            nodes = new int[n];
            Arrays.fill(nodes, -1);
            rank = new int[n];
        }

        public int find(int i) {
            int ans = nodes[i];
            if (ans < 0) {
                return i;
            } else {
                return nodes[i] = find(ans);
            }
        }

        public int union(int x, int y) {
            x = find(x);
            y = find(y);
            if (x == y) {
                return -1;
            } else if (rank[x] < rank[y]) {
                nodes[y] += nodes[x];
                nodes[x] = y;
            } else if (rank[x] == rank[y]) {
                rank[x]++;
                nodes[x] += nodes[y];
                nodes[y] = x;
            } else {
                nodes[x] += nodes[y];
                nodes[y] = x;
            }
            groups--;
            return x;
        }
    }

    static class FS {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        String next() {
            while(!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch(Exception ignored) {}
            }
            return st.nextToken();
        }
        int[] nextArray(int n) {
            int[] a = new int[n];
            for(int i = 0;i < n;i++) {
                a[i] = nextInt();
            }
            return a;
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}