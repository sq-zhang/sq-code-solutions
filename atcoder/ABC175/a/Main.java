package atcoder.ABC175.a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        String s = sc.next();
        int res = 0;
        for (int i = 0;i < s.length();i++) {
            if (s.charAt(i) == 'R') {
                res++;
            }
        }
        if (res == 3 || res <= 1) {
            pw.println(res);
        } else if (res == 2) {
            if (s.charAt(1) == 'R') {
                pw.println(2);
            } else {
                pw.println(1);
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