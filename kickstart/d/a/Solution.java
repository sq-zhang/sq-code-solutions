package kickstart.d.a;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

import static java.lang.System.exit;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    static int solve() {
        int n = sc.nextInt();
        int[] a = new int[n];
        for(int i = 0;i < n;i++) {
            a[i] = sc.nextInt();
        }
        int ans = 0, max = -1;
        for(int i = 0;i < n;i++) {
            if (a[i] > max && (i == n - 1 || a[i] > a[i + 1])) {
                ans++;
            }
            max = Math.max(a[i], max);
        }
        return ans;
    }

    public static void main(String[] args) {
        try {
            int tests = sc.nextInt();
            for (int test = 1; test <= tests; test++) {
                pw.println(String.format("Case #" + test + ": %d", solve()));
            }
            pw.flush();
        } catch (Throwable e) {
            e.printStackTrace();
            exit(1);
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
        String nextLine() {
            String s = "";
            try {
                s = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return s;
        }
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}
