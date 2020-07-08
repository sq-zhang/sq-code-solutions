package codeforces.round.round652.e;

import java.util.LinkedList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);


    public static void main(String[] args) {
        int n = sc.nextInt(), m = sc.nextInt();
        int[] w = sc.nextArray(n);
        int[][] wants = new int[m][2];
        List<Set<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new HashSet<>());
        }
        for (int i = 0; i < m; i++) {
            wants[i] = sc.nextArray(2);
            wants[i][0]--;
            wants[i][1]--;
            adj.get(wants[i][0]).add(i);
            adj.get(wants[i][1]).add(i);
        }
        List<Integer> res = new ArrayList<>();
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (adj.get(i).size() <= w[i]) {
                visited[i] = true;
                queue.addLast(i);
            }
        }
        while (!queue.isEmpty()) {
            int i = queue.removeFirst();
            List<Integer> ppl = new ArrayList<>(adj.get(i));
            for (int p : ppl) {
                res.add(p);
                int wantX = wants[p][0], wantY = wants[p][1];
                adj.get(wantX).remove(p);
                adj.get(wantY).remove(p);
                if (!visited[wantX] && adj.get(wantX).size() <= w[wantX]) {
                    visited[wantX] = true;
                    queue.addLast(wantX);
                }
                if (!visited[wantY] && adj.get(wantY).size() <= w[wantY]) {
                    visited[wantY] = true;
                    queue.addLast(wantY);
                }
            }
        }
        if (res.size() != m) {
            pw.println("DEAD");
        } else {
            pw.println("ALIVE");
            for (int i = m - 1;i >= 0;i--) {
                pw.print((res.get(i) + 1) + " ");
            }
            pw.println();
        }
        pw.flush();
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