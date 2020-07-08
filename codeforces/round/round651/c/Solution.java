package codeforces.round.round651.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    private static boolean isPrime(int n) {
        for(int i = 2;i <= Math.sqrt(n);i++) {
            if (n % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            boolean lose = (n == 1);
            if(n > 2 && n % 2 == 0){
                if((n & (n - 1)) == 0) {
                    lose = true;
                } else if(n % 4 != 0 && isPrime(n / 2)) {
                    lose = true;
                }
            }
            pw.println(lose ? "FastestFinger" : "Ashishgup");
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