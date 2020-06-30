package codeforces.edu.suffixarray.practice3.a;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

// 计数排序优化
public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        String t = sc.next();
        t += "$";
        int n = t.length();
        int[] p = calculateSuffixArray(t , n);
        int T = sc.nextInt();
        while (T-- > 0) {
            String s = sc.next();
            int m = s.length();
            if (m > n) {
                pw.println("No");
                continue;
            }

            if (binarySearch(t, s, p, m, n)) {
                pw.println("Yes");
            } else {
                pw.println("No");
            }
        }
        pw.flush();
    }

    static boolean binarySearch(String source, String target, int[] suffixArray, int m, int n) {
        int l = 0, r = n - 1;
        while(l <= r) {
            int mid = l + (r - l) / 2;
            int res = compareStr(source, suffixArray[mid], target, m, n);
            if (res == 0) {
                return true;
            } else if (res < 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return false;
    }

    static int compareStr(String source, int start, String target, int m, int n) {
        for(int i = 0;i < m;i++) {
            char s1 = target.charAt(i);
            char s2 = source.charAt((start + i) % n);
            if (s1 > s2) {
                return 1;
            } else if (s1 < s2) {
                return -1;
            }
        }
        return 0;
    }

    static int[] calculateSuffixArray(String s, int n) {
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
                p[i] = (p[i] - (1 << k) + n) % n;
            }

            p = countSort(p, c, n);

            int[] newC = new int[n];
            newC[p[0]] = 0;
            for(int i = 1;i < n;i++) {
                int preL = c[p[i - 1]], preR = c[(p[i - 1] + (1 << k)) % n];
                int curL = c[p[i]], curR = c[(p[i] + (1 << k)) % n];
                if (preL == curL && preR == curR) {
                    newC[p[i]] = newC[p[i - 1]];
                } else {
                    newC[p[i]] = newC[p[i - 1]] + 1;
                }
            }
            c = newC;
            k++;
        }

        return p;
    }

    static int[] countSort(int[] p, int[] c, int n) {
        int[] count = new int[n];
        for(int x : c) {
            count[x]++;
        }
        int[] pos = new int[n];
        for(int j = 1;j < n;j++) {
            pos[j] = pos[j - 1] + count[j - 1];
        }
        int[] res = new int[n];
        for(int x : p) {
            res[pos[c[x]]++] = x;
        }
        return res;
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