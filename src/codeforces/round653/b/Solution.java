package codeforces.round653.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            int two = 0, three = 0;
            while(n % 2 == 0) {
                n /= 2;
                two++;
            }
            while (n % 3 == 0) {
                n /= 3;
                three++;
            }
            if (n != 1 || two > three) {
                pw.println(-1);
            } else {
                pw.println(Math.max(two, three) + Math.abs(two - three));
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