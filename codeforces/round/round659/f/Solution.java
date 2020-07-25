package codeforces.round.round659.f;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt(), m = sc.nextInt();
        int[][] matrix = new int[n][m];
        for (int i = 0;i < n;i++) {
            matrix[i] = sc.nextArray(m);
        }
        int[] h = new int[n * m + 1];
        int[] v = new int[n * m + 1];
        for (int i = 0; i < n; ++i) {
            int max = 0;
            for (int j = 0; j < m; ++j)
                max = Math.max(max, matrix[i][j]);
            h[max] = 1;
        }

        for (int i = 0; i < m; ++i) {
            int max = 0;
            for (int j = 0; j < n; ++j)
                max = Math.max(max, matrix[j][i]);
            v[max] = 1;
        }
        int[][] ans = new int[n][m];
        Queue<Node> queue = new LinkedList<>();
        int x = -1, y = -1;
        for (int u = n * m;u >= 1;u--) {
            x += h[u];
            y += v[u];
            if (h[u] > 0 || v[u] > 0) {
                ans[x][y] = u;
            } else {
                Node node = queue.poll();
                ans[node.x][node.y] = u;
            }
            if (h[u] > 0) {
                for (int i = y - 1;i >= 0;i--) {
                    queue.add(new Node(x, i));
                }
            }
            if (v[u] > 0) {
                for (int i = x - 1;i >= 0;i--) {
                    queue.add(new Node(i, y));
                }
            }
        }
        for (int i = 0;i < n;i++) {
            for (int j = 0;j < m;j++) {
                pw.print(ans[i][j] + " ");
            }
            pw.println();
        }
        pw.flush();
    }

    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
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