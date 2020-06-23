package codeforces.round651.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.EnumSet;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        String s = sc.next();
        String t = sc.next();
        int[] diff = new int[n];
        int sum = 0;
        for(int i = 0;i < n;i++) {
            diff[i] = s.charAt(i) - t.charAt(i);
            sum += diff[i];
        }
        if (sum != 0) {
            pw.println(-1);
        } else {
            int cur1 = 0, cur2 = 0, max1 = 0, max2 = 0;
            for(int i = 0;i < n;i++) {
                cur1 += diff[i];
                cur2 -= diff[i];
                max1 = Math.max(max1, cur1);
                max2 = Math.max(max2, cur2);
                cur1 = Math.max(cur1, 0);
                cur2 = Math.max(cur2, 0);
            }
            pw.println(Math.max(max1, max2));
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