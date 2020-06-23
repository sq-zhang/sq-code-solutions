package codeforces.round651.a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

// https://www.geeksforgeeks.org/find-pair-maximum-gcd-array/
public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    /**
     * 最佳解法
    public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            pw.println(n / 2);
        }
        pw.flush();
    }
    */

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] divisors = new int[n + 1];
            for(int i = 1;i <= n;i++) {
                divisors[i]++;
            }
            int res = 1;
            for(int i = n;i >= 1;i--) {
                int j = i, counter = 0;
                while(j <= n) {
                    if (divisors[j] > 0) {
                        counter += divisors[j];
                    }
                    j += i;
                    if (counter == 2) {
                        res = i;
                        break;
                    }
                }
                if (res > 1) {
                    break;
                }
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