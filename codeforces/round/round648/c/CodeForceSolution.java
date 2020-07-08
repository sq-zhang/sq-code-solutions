package codeforces.round.round648.c;

import java.util.*;
import java.io.*;
/**
 * @author sqzhang
 * @date 2020/6/11
 * C. Rotation Matching
 * 数组 a 和 b 分别是 1 - n 的一个排列，可以任意平移两个数组
 * 求平移后 a 和 b 匹配的最大个数
 */
public class CodeForceSolution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] a = new int[n], b = new int[n];
        int[] pos = new int[n + 1];
        int[] offset = new int[n + 1];
        for(int i = 0;i < n;i++) {
            a[i]  = sc.nextInt();
            pos[a[i]] = i;
        }
        for(int i = 0;i < n;i++) {
            b[i] = sc.nextInt();
        }
        int res = 0;
        for(int i = 0;i < n;i++) {
            int cur = pos[b[i]] - i;
            if (cur < 0) {
                cur += n;
            }
            offset[cur]++;
            res = Math.max(res, offset[cur]);
        }
        pw.println(res);
        pw.flush();
    }

    static class FS {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer("");
        String next() {
            while(!st.hasMoreElements()) {
                try {
                    st = new StringTokenizer(br.readLine());
                } catch(Exception e) {}
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