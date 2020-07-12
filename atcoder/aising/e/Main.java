package atcoder.aising.e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[][] a = new int[n][3];
            for(int i = 0;i < n;i++) {
                a[i][0] = sc.nextInt();
                a[i][1] = sc.nextInt();
                a[i][2] = sc.nextInt();
            }
            Arrays.sort(a, (o1, o2) -> {
                int d1 = Math.abs(o1[1] - o1[2]);
                int d2 = Math.abs(o2[1] - o2[2]);
                if (d2 != d1) {
                    return d2 - d1;
                }
                return o2[0] - o1[0];
            });
            boolean[] vis = new boolean[n + 1];
            long ans = 0;
            for(int[] arr : a) {
                int target = arr[0], flag = arr[1] - arr[2];
                if (target == n) {
                    ans += arr[1];
                    continue;
                }
                if (flag > 0) {
                    while (target > 0 && vis[target]) {
                        target--;
                    }
                    if (target > 0) {
                        vis[target] = true;
                        ans += arr[1];
                    } else {
                        ans += arr[2];
                    }
                } else {
                    target = n;
                    while (target > arr[0] && vis[target]) {
                        target--;
                    }
                    if (target > arr[0]) {
                        vis[target] = true;
                        ans += arr[2];
                    } else {
                        ans += arr[1];
                    }
                }
            }
            pw.println(ans);
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