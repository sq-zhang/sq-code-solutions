package codeforces.round.round652.b;

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
            int n = sc.nextInt();
            String s = sc.next();
            if (s.indexOf('0') == -1 || s.indexOf('1') == -1) {
                pw.println(s);
                continue;
            }
            int left = 0, right = 0;
            for(int i = 0;i < s.length();i++) {
                if (s.charAt(i) == '1') {
                    left = i;
                    break;
                }
            }
            for(int i = s.length() - 1;i >= 0;i--) {
                if (s.charAt(i) == '0') {
                    right = i;
                    break;
                }
            }
            if (right <= left) {
                pw.println(s.substring(0, left) + s.substring(right + 1));
            } else {
                pw.println(s.substring(0, left) + "0" + s.substring(right + 1));
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
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}