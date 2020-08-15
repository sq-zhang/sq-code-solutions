package atcoder.ABC175.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] a = sc.nextArray(n);
        int res = 0;
        for(int i = 0;i < n;i++) {
            for(int j = i + 1;j < n;j++) {
                if (a[i] == a[j]) {
                    continue;
                }
                int sum = a[i] + a[j];
                int diff = a[i] - a[j];
                for(int k = j + 1;k < n;k++) {
                    if (a[i] == a[k] || a[j] == a[k]) {
                        continue;
                    }
                    if (sum > a[k] && diff + a[k] > 0
                        && a[k] > diff) {
                        res++;
                    }
                }
            }
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