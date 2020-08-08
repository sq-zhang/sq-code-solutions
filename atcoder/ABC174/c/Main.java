package atcoder.ABC174.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static int MAX_N = (int)1e6;
    public static void main(String[] args) {
        int k = sc.nextInt();
        if (k % 2 == 0) {
            System.out.println(-1);
            return;
        }
        int n = 7;
        for (int i = 1; i < MAX_N; i++) {
            if (n % k == 0) {
                System.out.println(i);
                return;
            }
            n = (10 * n + 7) % k;
        }
        System.out.println(-1);
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