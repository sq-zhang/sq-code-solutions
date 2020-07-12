package atcoder.aising.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] ans = new int[n + 1];
        for(int x = 1;x <= n;x++) {
            int x2 = x * x;
            if (x2 > n) {
                break;
            }
            for(int y = 1;y <= n;y++) {
                int y2 = y * y, xy = x * y;
                if (x2 + y2 + xy > n) {
                    break;
                }
                for(int z = 1; z <= n;z++) {
                    int num = x2 + y2 + z * z + xy + y * z + z * x;
                    if (num > n) {
                        break;
                    }
                    ans[num]++;
                }
            }
        }
        for(int i = 1;i <= n;i++) {
            pw.println(ans[i]);
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