package codeforces.round.round648.g;

import java.util.*;
import java.io.*;
/**
 * @author sqzhang
 * @date 2020/6/11
 * G. Secure Password
 * 题目描述：猜密码，加密后的数组的第 i 位数是除 i 外所有数字的或值
 * 你最多可以猜 13 次
 */
public class CodeForceSolution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static int Q = 13;

    static long ask(ArrayList<Integer> toAsk) {
        pw.print("? " + toAsk.size());
        for (int x : toAsk)
            pw.print(" " + (x + 1));
        pw.println();
        pw.flush();
        return sc.nextLong();
    }


    public static void main(String[] args) {
        int n = sc.nextInt();

        int[] mask = new int[n];
        for (int i = 0, j = 0; i < n; j++) {
            if (Integer.bitCount(j) == Q / 2)
                mask[i++] = j;
        }

        long[] or = new long[Q];
        for (int k = 0; k < Q; k++) {
            ArrayList<Integer> toAsk = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if ((mask[i] & (1 << k)) != 0)
                    toAsk.add(i);
            }
            or[k] = toAsk.size() > 0 ? ask(toAsk) : 0;
        }

        long[] ans = new long[n];
        for (int i = 0; i < n; i++) {
            for (int k = 0; k < Q; k++) {
                if ((mask[i] & (1 << k)) == 0)
                    ans[i] |= or[k];
            }
        }

        pw.print("!");
        for (int i = 0; i < n; i++)
            pw.print(" " + ans[i]);
        pw.println();
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