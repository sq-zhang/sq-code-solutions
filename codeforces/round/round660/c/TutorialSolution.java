package codeforces.round.round660.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class TutorialSolution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static int[] p, h, nodes, good;
    static List<List<Integer>> adj;
    static boolean res;
    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt(), m = sc.nextInt();
            p = sc.nextArray(n);
            h = sc.nextArray(n);
            good = new int[n];
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
            res = true;
            dfs(0, -1);
            pw.println(res ? "YES" : "NO");
        }
        pw.flush();
    }

    static void dfs(int u, int parent) {
        nodes[u] = p[u];
        int goodCount = 0;
        for(Integer v : adj.get(u)) {
            if (v == parent) {
                continue;
            }
            dfs(v, u);
            goodCount += good[v];
            nodes[u] += nodes[v];
        }
        if ((nodes[u] + h[u]) % 2 != 0) {
            res = false;
        }
        good[u] = (nodes[u] + h[u]) / 2;
        if (good[u] < 0 || good[u] > nodes[u]) {
            res = false;
        }
        if (goodCount > good[u]) {
            res = false;
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