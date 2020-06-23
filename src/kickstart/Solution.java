package kickstart;

import java.util.*;
import java.io.*;
import static java.lang.System.exit;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static double eps = 1e-10;
    static int[] nums;
    static int n;

    static double calculate(double mid) {
        double res = 0.0;
        mid += 1.0;
        for(int i = 0;i <= n;i++) {
            double cur = 1.0;
            for(int j = 0;j <= n - i;j++) {
                cur *= mid;
            }
            res += cur * nums[i];
        }
        return res;
    }

    static double solve() throws Exception {
        n = sc.nextInt();
        nums = new int[n + 1];
        for(int i = 0;i <= n;i++) {
            nums[i] = sc.nextInt();
        }
        nums[0] = -nums[0];
        double l = -1.0, r = 1.0, mid = 0.0;
        while(Math.abs(r - l) > eps) {
            mid = (l + r) / 2;
            if (calculate(mid) > 0.0) {
                l = mid;
            } else {
                r = mid;
            }
        }
        return mid;
    }

    public static void main(String[] args) {
        try {
            int tests = sc.nextInt();
            for (int test = 1; test <= tests; test++) {
                pw.println(String.format("Case #" + test + ": %.10f", solve()));
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
