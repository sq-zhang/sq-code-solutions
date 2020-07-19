package codeforces.round.round657.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            long l = sc.nextLong(), r = sc.nextLong(), m = sc.nextLong();
            long left = m - (r - l), right = m + r - l;
            for(long a = l;a <= r;a++) {
                long m2 = right / a * a;
                if (m2 >= left) {
                    long b = -1, c = -1;
                    for(long i = l;i <= r;i++) {
                        b = i;
                        c = m2 - m + b;
                        if (c >= l && c <= r) {
                            break;
                        }
                    }
                    pw.println(a + " " + b + " " + c);
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