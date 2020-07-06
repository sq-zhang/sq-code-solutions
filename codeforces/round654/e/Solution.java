package codeforces.round654.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt(), p = sc.nextInt();
        int[] a = sc.nextArray(n);
        Arrays.sort(a);
        int left = 1, right = (int) 1e9;
        int first = (int) 1e9 + 1;
        while (left <= right) {
            int x = left + (right - left) / 2;
            boolean valid = true;
            for (int i = 0; i < n; i++) {
                if (a[i] > x + i) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                right = x - 1;
                first = x;
            } else {
                left = x + 1;
            }
        }
        int last = first - 1;
        left = first;
        right = (int) 1e9;
        while (left <= right) {
            int x = left + (right - left) / 2;
            boolean valid = true;
            for (int i = n - 1; i >= 0; i--) {
                int c = Math.max(0, a[i] - x);
                int options = i + 1 - c;
                if (options % p == 0) {
                    valid = false;
                    break;
                }
            }
            if (valid) {
                left = x + 1;
                last = x;
            } else {
                right = x - 1;
            }
        }
        pw.println(last - first + 1);
        for (int i = first; i <= last; i++) {
            pw.print(i + " ");
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