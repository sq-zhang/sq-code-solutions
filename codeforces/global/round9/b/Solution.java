package codeforces.global.round9.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);
    static final int[][] dirs = {{0, 1}, {1, 0}, {-1, 0}, {0, -1}};
    static int[][] matrix;
    static int n, m;

    private static boolean valid(int nx, int ny) {
        return nx >= 0 && nx < n && ny >= 0 && ny < m;
    }

    public static boolean solve() {
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < m;j++) {
                int count = 0;
                for(int[] d : dirs) {
                    int nx = i + d[0], ny = j + d[1];
                    if (valid(nx, ny)) {
                        count++;
                    }
                }
                if (matrix[i][j] > count) {
                    return false;
                }
                matrix[i][j] = count;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            n = sc.nextInt();
            m = sc.nextInt();
            matrix = new int[n][m];
            for(int i = 0;i < n;i++) {
                matrix[i] = sc.nextArray(m);
            }

            if (!solve()) {
                pw.println("NO");
            } else {
                pw.println("YES");
                for(int i = 0;i < n;i++) {
                    for(int j = 0;j < m;j++) {
                        pw.print(matrix[i][j] + " ");
                    }
                    pw.println();
                }
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