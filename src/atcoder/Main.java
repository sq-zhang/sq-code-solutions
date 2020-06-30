package atcoder;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt(), q = sc.nextInt();
        char[] s = new char[n];
        for(int i = 0;i < n;i++) {
            s[i] = (char)(i + 'A');
        }
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < n - 1;j++) {
                pw.println("? " + s[j] + " " + s[j + 1]);
                pw.flush();
                String ans = sc.next();
                if (ans.equals(">")) {
                    char t = s[j];
                    s[j] = s[j + 1];
                    s[j + 1] = t;
                }
            }
        }
        pw.println("! " + new String(s));
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