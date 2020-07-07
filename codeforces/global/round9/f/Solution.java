package codeforces.global.round9.f;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        long a = sc.nextLong(), b = sc.nextLong(), c = sc.nextLong();

        pw.println("First");
        pw.flush();

        while (true) {
            long[] arr = {a, b, c};
            Arrays.sort(arr);
            long y;
            if (arr[1] + arr[1] == arr[0] + arr[2]) {
                y = arr[1] - arr[0];
            } else {
                y = arr[2] + arr[2] - arr[0] - arr[1];
            }
            pw.println(y);
            pw.flush();
            int i = sc.nextInt();
            if (i == 0) break;
            else if (i == 1) a += y;
            else if (i == 2) b += y;
            else if (i == 3) c += y;
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