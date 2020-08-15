package atcoder.ABC175.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类

    public static void main(String[] args) {
        long x = sc.nextLong(), k = sc.nextLong(), d = sc.nextLong();
        long m = Math.abs(x) / d;
        if (m >= k) {
            System.out.println(x < 0 ? -x - d * k : x - d * k);
            return;
        }
        if (x < 0) {
            x += m * d;
            k -= m;
            if (k % 2 == 0) {
                System.out.println(Math.min(-x, x + d));
            } else {
                System.out.println(Math.min(Math.abs(-x + d), Math.abs(-x - d)));
            }
        } else {
            x -= m * d;
            k -= m;
            if (k % 2 == 0) {
                System.out.println(Math.min(x, d - x));
            } else {
                System.out.println(Math.min(Math.abs(x + d), Math.abs(x - d)));
            }
        }
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