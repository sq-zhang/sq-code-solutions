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
        long[] h = sc.nextLongArray(n);
        long[] w = sc.nextLongArray(m);
        Arrays.sort(h);

        long[] left = new long[n + 1];
        long[] right = new long[n + 1];
        for (int i = 2;i < n;i += 2) {
            left[i] = left[i - 2] + h[i - 1] - h[i - 2];
            right[i] = right[i - 2] + h[n - i + 1] - h[n - i];
        }
        pw.println(Arrays.toString(left));
        pw.println(Arrays.toString(right));
        long res = Long.MAX_VALUE;
        for (int i = 0;i < m;i++) {
            int x = searchRight(h, w[i]);
            if (x % 2 == 0) {
                res = Math.min(res, left[x] + right[n - x - 1] + h[x] - w[i]);
            } else {
                res = Math.min(res, left[x - 1] + right[n - x] + w[i] - h[x - 1]);
            }
        }
        pw.println(res);
        pw.flush();
    }

    public static int searchRight(long[] nums, long x) {
        int l = -1, r = nums.length;
        while (Math.abs(l - r) > 1) {
            int mid = (l + r) / 2;
            if (nums[mid] > x) {
                r = mid;
            } else {
                l = mid;
            }
        }
        return r;
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
        long[] nextLongArray(int n) {
            long[] a = new long[n];
            for(int i = 0;i < n;i++) {
                a[i] = nextLong();
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