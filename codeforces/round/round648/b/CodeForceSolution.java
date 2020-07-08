package codeforces.round.round648.b;

import java.util.*;
import java.io.*;
/**
 * @author sqzhang
 * @date 2020/6/11
 * B. Trouble Sort
 * 输入数组 a 和 b，a 是数字，b 是 type = 0 或 1，只有 0 和 1 类型的数字可以交换
 * 问能否交换使数组单调递增
 * 输出 Yes 或 No
 */
public class CodeForceSolution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static int maxN = 500;
    static int[] arr = new int[maxN];
    public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-- > 0) {
            int n = sc.nextInt();
            boolean sorted = true, have0 = false, have1 = false;
            for(int i = 0;i < n;i++) {
                arr[i] = sc.nextInt();
                if (i > 0 && arr[i - 1] > arr[i]) {
                    sorted = false;
                }
            }
            for(int i = 0;i < n;i++) {
                int num = sc.nextInt();
                if (num == 1) {
                    have1 = true;
                } else {
                    have0 = true;
                }
            }
            if (sorted || (have0 && have1)) {
                pw.println("Yes");
            } else {
                pw.println("No");
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