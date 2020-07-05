package atcoder.ABC173.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        Map<String, Integer> maps = new HashMap<>();
        while (n-- > 0) {
            String s = sc.next();
            maps.put(s, maps.getOrDefault(s, 0) + 1);
        }
        pw.println("AC x " + maps.getOrDefault("AC", 0));
        pw.println("WA x " + maps.getOrDefault("WA", 0));
        pw.println("TLE x " + maps.getOrDefault("TLE", 0));
        pw.println("RE x " + maps.getOrDefault("RE", 0));
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