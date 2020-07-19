package codeforces.round.round657.a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static final char[] pattern = {'a', 'b', 'a', 'c', 'a', 'b', 'a'};

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            String s = sc.next(), ans = "";
            for(int i = 0;i <= n - 7;i++) {
                char[] ns = s.toCharArray();
                boolean match = true;
                for(int j = 0;j < 7;j++) {
                    if (ns[i + j] != pattern[j] && ns[i + j] != '?') {
                        match = false;
                    } else {
                        ns[i + j] = pattern[j];
                    }
                }
                if (!match) {
                    continue;
                }
                for(int j = 0;j < n;j++) {
                    if (ns[j] == '?') {
                        ns[j] = 'd';
                    }
                }
                for(int j = 0;j <= n - 7;j++) {
                    if (j == i) {
                        continue;
                    }
                    boolean secondMatch = true;
                    for(int k = 0;k < 7;k++) {
                        if (ns[j + k] != pattern[k] && ns[j + k] != '?') {
                            secondMatch = false;
                            break;
                        }
                    }
                    if (secondMatch) {
                        match = false;
                        break;
                    }
                }
                if (match) {
                    ans = new String(ns);
                    break;
                }
            }
            if (ans.length() == 0) {
                pw.println("NO");
            } else {
                pw.println("YES");
                pw.println(ans);
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