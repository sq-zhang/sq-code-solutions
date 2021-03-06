package codeforces.round.round660.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        long[] a = new long[n];
        for (int i = 0;i < n;i++) {
            a[i] = sc.nextLong();
        }
        int[] b = new int[n];
        int[] size = new int[n];
        for(int i  = 0;i < n;i++) {
            b[i] = sc.nextInt() - 1;
            if (b[i] >= 0) {
                size[b[i]]++;
            }
        }
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0;i < n;i++) {
            if (size[i] == 0) {
                q.add(i);
            }
        }
        long ans = 0;
        List<Integer> wait = new ArrayList<>();
        List<Integer> now = new ArrayList<>();
        while (!q.isEmpty()) {
            int v = q.poll();
            ans += a[v];
            if (a[v] >= 0) {
                if (b[v] >= 0) {
                    a[b[v]] += a[v];
                }
                now.add(v);
            } else {
                wait.add(v);
            }
            if (b[v] >= 0) {
                size[b[v]]--;
                if (size[b[v]] == 0) {
                    q.add(b[v]);
                }
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