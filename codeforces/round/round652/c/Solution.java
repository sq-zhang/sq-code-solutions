package codeforces.round.round652.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt(), k = sc.nextInt();
            Integer[] a = new Integer[n], w = new Integer[k];
            for(int i = 0;i < n;i++) {
                a[i] = sc.nextInt();
            }
            for(int i = 0;i < k;i++) {
                w[i] = sc.nextInt();
            }
            Arrays.sort(a, (o1, o2) -> o2 - o1);
            Arrays.sort(w);
            long res = 0;
            int i1 = k, l = 0, r = n -1;
            for(int i = 0;i < k;i++) {
                if (w[i] > 1) {
                    i1 = i;
                    break;
                }
                res += a[l] * 2;
                l++;
            }
            for(int j = k - 1;j >= i1;j--) {
                res += a[l] + a[r];
                r = r - w[j] + 1;
                l++;
            }
            pw.println(res);
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
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}