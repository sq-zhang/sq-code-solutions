package codeforces.edu.suffixarray.practice5.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        String s1 = sc.next(), s2 = sc.next();
        String s = s1 + "#" + s2;
        SuffixArray sa = new SuffixArray(s);
        int res = 0, m = s1.length(), idx = 0;
        for(int i = 1;i < sa.n - 1;i++) {
            if (sa.sa[i] < m && sa.sa[i + 1] < m) {
                continue;
            }
            if (sa.sa[i] > m && sa.sa[i + 1] > m) {
                continue;
            }
            if (sa.lcp[i] > res) {
                res = sa.lcp[i];
                idx = sa.sa[i];
            }
        }

        pw.println(s.substring(idx, idx + res));
        pw.flush();
    }

    static class SuffixArray {
        public int n;
        public int[] sa, lcp;
        public String s;

        public SuffixArray(String s) {
            this.s = s + "$";
            this.n = this.s.length();
            sa = this.calculateSa();
            lcp = calculateLcp();
        }

        private  int[] calculateLcp() {
            int[] lcp = new int[n];
            int[] rp = new int[n];
            for(int i = 0;i < n;i++) {
                rp[sa[i]] = i;
            }
            int k = 0;
            for(int i = 0;i < n;i++) {
                if (rp[i] == n - 1) {
                    k = 0;
                    continue;
                }
                int j = sa[rp[i] + 1];
                while(i + k < n && j + k < n && s.charAt(i + k) == s.charAt(j + k)) {
                    k++;
                }
                lcp[rp[i]] = k;
                if (k > 0) {
                    k--;
                }
            }
            return lcp;
        }

        private int[] calculateSa() {
            int[] sa = new int[n], c = new int[n];
            int[][] a = new int[n][];
            for(int i = 0;i < n;i++) {
                a[i] = new int[]{s.charAt(i), i};
            }
            Arrays.sort(a, Comparator.comparingInt(o -> o[0]));

            for(int i = 0;i < n;i++) {
                sa[i] = a[i][1];
            }
            c[sa[0]] = 0;
            for(int i = 1;i < n;i++) {
                if (a[i][0] == a[i - 1][0]) {
                    c[sa[i]] = c[sa[i - 1]];
                } else {
                    c[sa[i]] = c[sa[i - 1]] + 1;
                }
            }

            int k = 0;
            while((1 << k) < n) {
                for(int i = 0;i < n;i++) {
                    sa[i] = (sa[i] - (1 << k) + n) % n;
                }

                sa = countSort(sa, c, n);

                int[] newC = new int[n];
                newC[sa[0]] = 0;
                for(int i = 1;i < n;i++) {
                    int preL = c[sa[i - 1]], preR = c[(sa[i - 1] + (1 << k)) % n];
                    int curL = c[sa[i]], curR = c[(sa[i] + (1 << k)) % n];
                    if (preL == curL && preR == curR) {
                        newC[sa[i]] = newC[sa[i - 1]];
                    } else {
                        newC[sa[i]] = newC[sa[i - 1]] + 1;
                    }
                }
                c = newC;
                k++;
            }

            return sa;
        }

        private int[] countSort(int[] p, int[] c, int n) {
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