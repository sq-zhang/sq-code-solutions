package codeforces.edu.suffixarray.practice2;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 基数排序优化
public class RadixSortSolution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        String s = sc.next();
        s += "$";
        int n = s.length();
        int[] p = new int[n], c = new int[n];
        int[][] a = new int[n][];
        for(int i = 0;i < n;i++) {
            a[i] = new int[]{s.charAt(i), i};
        }
        Arrays.sort(a, Comparator.comparingInt(o -> o[0]));

        for(int i = 0;i < n;i++) {
            p[i] = a[i][1];
        }
        c[p[0]] = 0;
        for(int i = 1;i < n;i++) {
            if (a[i][0] == a[i - 1][0]) {
                c[p[i]] = c[p[i - 1]];
            } else {
                c[p[i]] = c[p[i - 1]] + 1;
            }
        }

        int k = 0;
        while((1 << k) < n) {
            for(int i = 0;i < n;i++) {
                a[i] = new int[]{c[i], c[(i + ((1 << k))) % n], i};
            }
            // Arrays.sort(a, ((o1, o2) -> o1[0] == o2[0] ? o1[1] - o2[1] : o1[0] - o2[0]));
            a = radixSort(a, n);
            for(int i = 0;i < n;i++) {
                p[i] = a[i][2];
            }
            c[p[0]] = 0;
            for(int i = 1;i < n;i++) {
                if (a[i][0] == a[i - 1][0] && a[i][1] == a[i - 1][1]) {
                    c[p[i]] = c[p[i - 1]];
                } else {
                    c[p[i]] = c[p[i - 1]] + 1;
                }
            }
            k++;
        }

        for (int i = 0;i < n;i++) {
            pw.print(p[i] + " ");
        }
        pw.println();
        pw.flush();
    }

    static int[][] radixSort(int[][] a, int n) {
        for(int i = 1;i >= 0;i--) {
            int[][] sortedA = new int[n][];
            int[] count = new int[n];
            for(int j = 0;j < n;j++) {
                count[a[j][i]]++;
            }
            int[] pos = new int[n];
            for(int j = 1;j < n;j++) {
                pos[j] = pos[j - 1] + count[j - 1];
            }
            for(int[] aa : a) {
                sortedA[pos[aa[i]]++] = aa;
            }
            a = sortedA;
        }
        return a;
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