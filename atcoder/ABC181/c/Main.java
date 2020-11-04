package atcoder.ABC181.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[][] points = new int[n][2];
        int[] xPoints = new int[2001];
        int[] yPoints = new int[2001];
        boolean line = false;
        for (int i = 0;i < n;i++) {
            points[i][0] = sc.nextInt();
            points[i][1] = sc.nextInt();
            xPoints[points[i][0] + 1000]++;
            yPoints[points[i][1] + 1000]++;
            if (xPoints[points[i][0] + 1000] >= 3 ||
                yPoints[points[i][1] + 1000] >= 3) {
                line = true;
            }
        }
        pw.println(line || valid(points, n) ? "Yes" : "No");
        pw.flush();
    }

    static boolean valid(int[][] points, int n) {
        for (int i = 0;i < n;i++) {
            for (int j = i + 1;j < n;j++) {
                if (xEqualOryEqual(points[i], points[j])) {
                    continue;
                }
                for (int k = j + 1;k < n;k++) {
                    if (xEqualOryEqual(points[i], points[k]) ||
                        xEqualOryEqual(points[j], points[k])) {
                        continue;
                    }
                    if ((points[k][1]-points[i][1])*(points[j][0]-points[i][0]) -
                        (points[j][1]-points[i][1])*(points[k][0]-points[i][0]) == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    static boolean xEqualOryEqual(int[] p1, int[] p2) {
        return p1[0] == p2[0] || p1[1] == p2[1];
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