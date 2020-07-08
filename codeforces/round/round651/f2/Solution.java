package codeforces.round.round651.f2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static int n, U, D, ans1, ans2;
    static ArrayList<Integer>[] g;
    static int[] par, dep;

    static void ask(ArrayList<Integer> toAsk) {
        pw.print("? " + toAsk.size());
        for (int u : toAsk)
            pw.print(" " + (u + 1));
        pw.println();
        pw.flush();
        U = sc.nextInt() - 1;
        D = sc.nextInt();
    }

    static void dfs(int u) {
        for (int v : g[u]) {
            if (par[v] == -1) {
                par[v] = u;
                dep[v] = dep[u] + 1;
                dfs(v);
            }
        }
    }

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            n = sc.nextInt();
            g = new ArrayList[n];
            Arrays.setAll(g, i -> new ArrayList<>());
            for (int i = 0; i < n - 1; i++) {
                int u = sc.nextInt() - 1;
                int v = sc.nextInt() - 1;
                g[u].add(v);
                g[v].add(u);
            }

            ArrayList<Integer> toAsk = new ArrayList<>();
            for (int u = 0; u < n; u++)
                toAsk.add(u);

            ask(toAsk);

            int root = U;
            int len = D;

            par = new int[n];
            Arrays.fill(par, -1);
            dep = new int[n];
            par[root] = root;
            dep[root] = 0;
            dfs(root);

            int maxDep = 0;
            for (int u = 0; u < n; u++)
                maxDep = Math.max(maxDep, dep[u]);

            int low = (len + 1) / 2 - 1, high = Math.min(len, maxDep);
            while (low < high) {
                int mid = (low + high + 1) / 2;
                toAsk.clear();
                for (int u = 0; u < n; u++) {
                    if (dep[u] == mid)
                        toAsk.add(u);
                }
                ask(toAsk);
                if (D == len) {
                    low = mid;
                    ans1 = U;
                } else
                    high = mid - 1;
            }

            int not = ans1;
            while (dep[not] != len - dep[ans1])
                not = par[not];

            toAsk.clear();
            for (int u = 0; u < n; u++) {
                if (dep[u] == len - dep[ans1] && u != not)
                    toAsk.add(u);
            }

            if (toAsk.size() > 0) {
                ask(toAsk);
                ans2 = U;
            } else
                ans2 = root;

            pw.println("! " + (ans1 + 1) + " " + (ans2 + 1));
            pw.flush();
            if (!sc.next().equals("Correct")) {
                pw.flush();
                System.exit(0);
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
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}