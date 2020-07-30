package codeforces.round.round660.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static int[] p, h, nodes;
    static int m, n;
    static List<List<Integer>> adj;
    static boolean[] vis1, vis2;
    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            n = sc.nextInt();
            m = sc.nextInt();
            p = sc.nextArray(n);
            h = sc.nextArray(n);
            nodes = new int[n];
            adj = new ArrayList<>();
            for(int i = 0;i < n;i++) {
                adj.add(new ArrayList<>());
            }
            for(int i = 0;i < n - 1;i++) {
                int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
                adj.get(u).add(v);
                adj.get(v).add(u);
            }
            vis1 = new boolean[n];
            vis2 = new boolean[n];
            dfs(0);
            if (dfs2(0).z == 1) {
                pw.println("YES");
            } else {
                pw.println("NO");
            }
        }
        pw.flush();
    }

    static Pair dfs2(int u) {
        vis2[u] = true;
        int i = nodes[u] + h[u], j = nodes[u] - h[u];
        if (i < 0 || j < 0 || i % 2 == 1 || j % 2 == 1) {
            return new Pair(i, j, -1);
        }
        i /= 2;
        j /= 2;
        int ii = 0, jj = 0;
        boolean hasChildren = false;
        for (Integer v : adj.get(u)) {
            if (vis2[v]) {
                continue;
            }
            hasChildren = true;
            Pair child = dfs2(v);
            if (child.z == -1) {
                return new Pair(i, j, -1);
            }
            ii += child.x;
            jj += child.y;
        }
        if (hasChildren && (ii > i)) {
            return new Pair(i, j, -1);
        }
        return new Pair(i, j, 1);
    }

    static int dfs(int u) {
        vis1[u] = true;
        int sum = p[u];
        for (Integer v : adj.get(u)) {
            if (vis1[v]) {
                continue;
            }
            sum += dfs(v);
        }
        nodes[u] = sum;
        return sum;
    }

    static class Pair {
        int x, y, z;
        public Pair(int x, int y, int z) {
            this.x = x;
            this.y = y;
            this.z = z;
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