package atcoder.ABC174.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        String s = sc.next();
        int ans = 0, l = 0, r = n - 1;
        while (l < r) {
            while (l < n && s.charAt(l) == 'R') {
                l++;
            }
            if (l >= r) {
                break;
            }
            while (r >= 0 && s.charAt(r) == 'W') {
                r--;
            }
            if (l >= r) {
                break;
            }
            l++;
            r--;
            ans++;
        }
        pw.println(ans);
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