package codeforces.round.round659.d;

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
            int x = 0;
            for (int i = 0;i < n;i++) {
                a[i] = sc.nextInt();
                x ^= a[i];
            }
            if (x == 0) {
                pw.println("DRAW");
                continue;
            }
            for (int k = 30;k >= 0;k--) {
                if (((x >> k) & 1) == 1) {
                    int[] count = new int[2];
                    for (int num : a) {
                        count[num >> k & 1]++;
                    }
                    if (count[1] % 4 == 3 && count[0] % 2 == 0) {
                        pw.println("LOSE");
                    } else {
                        pw.println("WIN");
                    }
                    break;
                }
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