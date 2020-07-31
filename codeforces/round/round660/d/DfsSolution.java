package codeforces.round.round660.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class DfsSolution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static boolean[] vis;
    static List<List<Integer>> adj;
    static List<Integer> wait, now;
    static long ans;
    static long[] a;
    static int[] b;
    public static void main(String[] args) {
        int n = sc.nextInt();
        a = new long[n];
        for (int i = 0;i < n;i++) {
            a[i] = sc.nextLong();
        }
        b = new int[n];
        adj = new ArrayList<>();
        for (int i = 0;i < n;i++) {
            adj.add(new ArrayList<>());
        }
        for(int i  = 0;i < n;i++) {
            b[i] = sc.nextInt() - 1;
            if (b[i] >= 0) {
                adj.get(b[i]).add(i);
            }
        }
        vis = new boolean[n];
        ans = 0;
        wait = new ArrayList<>();
        now = new ArrayList<>();
        for (int i = 0;i < n;i++) {
            if (!vis[i]) {
                dfs(i);
            }
        }

        pw.println(ans);
        for (int i = 0;i < now.size();i++) {
            pw.print((now.get(i) + 1) + " ");
        }
        for (int i = wait.size() - 1;i >= 0;i--) {
            pw.print((wait.get(i) + 1) + " ");
        }
        pw.println();
        pw.flush();
    }

    private static void dfs(int v) {
        vis[v] = true;
        for (Integer u : adj.get(v)) {
            if (!vis[u]) {
                dfs(u);
            }
        }
        ans += a[v];
        if (a[v] >= 0) {
            if (b[v] >= 0) {
                a[b[v]] += a[v];
            }
            now.add(v);
        } else {
            wait.add(v);
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