package codeforces.round653.e1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt(), k = sc.nextInt();
        int[] t = new int[n];
        Set<Integer> a = new HashSet<>(), b = new HashSet<>();
        for(int i = 0;i < n;i++) {
            t[i] = sc.nextInt();
            if (sc.nextInt() == 1) {
                a.add(i);
            }
            if (sc.nextInt() == 1) {
                b.add(i);
            }
        }

        if (a.size() < k || b.size() < k) {
            pw.println(-1);
        } else {

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