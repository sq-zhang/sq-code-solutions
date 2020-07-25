package atcoder.MSPC2020.a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int x = sc.nextInt();
        if (x >= 400 && x < 600) {
            pw.println(8);
        } else if (x >= 600 && x < 800) {
            pw.println(7);
        } else if (x >= 800 && x < 1000) {
            pw.println(6);
        } else if (x >= 1000 && x < 1200) {
            pw.println(5);
        } else if (x >= 1200 && x < 1400) {
            pw.println(4);
        } else if (x >= 1400 && x < 1600) {
            pw.println(3);
        } else if (x >= 1600 && x < 1800) {
            pw.println(2);
        } else {
            pw.println(1);
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