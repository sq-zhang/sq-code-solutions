package codeforces.round.round659.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            int max = 0;
            for(int i = 0;i < n;i++) {
                a[i] = sc.nextInt();
                max = Math.max(max, a[i]);
            }
            if (max == 0) {
                for(int i = 0;i <= n;i++) {
                    pw.println((char)((i % 26) + 'a'));
                }
                continue;
            }
            char[] s = new char[max];
            for(int i = 0;i < max;i++) {
                s[i] = 'a';
            }
            pw.println(new String(s));
            for(int i = 0;i < n;i++) {
                for(int j = a[i];j < max;j++) {
                    s[j] = (char)(((s[j] + 1 - 'a') % 26) + 'a');
                }
                pw.println(new String(s));
            }
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