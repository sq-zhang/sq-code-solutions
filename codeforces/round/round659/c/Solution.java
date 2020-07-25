package codeforces.round.round659.c;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static int alp = 20;
    public static void main(String[] args) throws IOException {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            char[] a = sc.next().toCharArray();
            char[] b = sc.next().toCharArray();
            int ans = 0;
            List<List<Integer>> adj = new ArrayList<>();
            for(int i = 0;i < alp;i++) {
                adj.add(new ArrayList<>());
            }
            for(int i = 0;i < n;i++) {
                if (a[i] > b[i]) {
                    ans = -1;
                    break;
                } else if (a[i] < b[i]) {
                    adj.get(a[i] - 'a').add(b[i] - 'a');
                    adj.get(b[i] - 'a').add(a[i] - 'a');
                }
            }
            if (ans == -1) {
                pw.println(ans);
                continue;
            }
            boolean[] vis = new boolean[alp];
            for (int i = 0;i < alp;i++) {
                if (!vis[i]) {
                    dfs(adj, i, vis);
                    ans++;
                }
            }
            pw.println(alp - ans);
        }
        pw.flush();
    }

    private static void dfs(List<List<Integer>> adj, int u, boolean[] vis) {
        vis[u] = true;
        for (Integer v : adj.get(u)) {
            if (!vis[v]) {
                dfs(adj, v, vis);
            }
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