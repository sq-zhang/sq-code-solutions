package codeforces.round648.f;

import java.util.*;
import java.io.*;
/**
 * @author sqzhang
 * @date 2020/6/11
 * F. Swaps Again
 * 题目描述：给定数组 a 和 b，每次选定 k（1 <= k <= 2/n），将前 k 个和后 k 个交换
 * 如果能将 a 交换成 b，则称该数组是 valid，否则为 invalid
 * 输入：t, n, 接着两次 n 个数组
 * 输出：Yes 或 No
 */
public class CodeForceSolution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = Math.min(x, y);
            this.y = Math.max(x, y);
        }

        @Override
        public boolean equals(Object o) {
            Pair pair = (Pair) o;
            return x == pair.x &&
                y == pair.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n], b = new int[n];
            for (int i = 0;i < n;i++) {
                a[i] = sc.nextInt();
            }
            for(int i = 0;i < n;i++) {
                b[i] = sc.nextInt();
            }
            boolean res = true;
            Map<Pair, Integer> maps = new HashMap<>();
            if (n % 2 == 1 && a[n / 2] != b[n / 2]) {
                res = false;
            } else {
                for(int i = 0;i < n / 2;i++) {
                    Pair p = new Pair(a[i], a[n - 1 - i]);
                    maps.put(p, maps.getOrDefault(p, 0) + 1);
                }
                for(int i = 0;i < n / 2;i++) {
                    Pair p = new Pair(b[i], b[n - 1 - i]);
                    int c = maps.getOrDefault(p, 0);
                    if (c == 0) {
                        res = false;
                        break;
                    } else {
                        maps.put(p, c - 1);
                    }
                }
            }
            pw.println(res ? "Yes" : "No");

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
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}