package atcoder.ABC181.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt(), m = sc.nextInt();
        int[] h = sc.nextArray(n);
        int[] w = sc.nextArray(m);
        Arrays.sort(h);
        Arrays.sort(w);
        int res = Integer.MAX_VALUE;
        int k = 0;
        for (int wi : w) {
            int sum = 0, i = 0;
            boolean flag = false;
            while (i < n - 1) {
                if (h[i + 1] >= wi && !flag) {
                    sum += Math.abs(h[i] - wi);
                    i += 1;
                    flag = true;
                } else {
                    sum += h[i + 1] - h[i];
                    i += 2;
                }
            }
            if (!flag) {
                sum += Math.abs(wi - h[n - 1]);
            }
            res = Math.min(res, sum);
        }
        pw.println(res);
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