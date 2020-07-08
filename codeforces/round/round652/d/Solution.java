package codeforces.round.round652.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static int mod = (int)(1e9 + 7);
    public static void main(String[] args) {
        long[] dp = new long[2000_001];
        dp[3] = 4;
        for (int i = 4; i <= 2e6; i++) {
            dp[i] = (dp[i - 1] + 2 * dp[i - 2] + (i % 3 == 0 ? 4 : 0)) % mod;
        }

        int t = sc.nextInt();
        while(t-- > 0){
            pw.println(dp[sc.nextInt()]);
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