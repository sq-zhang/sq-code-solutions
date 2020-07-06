package codeforces.round648.a;

/**
 * @author sqzhang
 * @date 2020/6/11
 * A. Matrix Game
 * Ashish 和 Vivek 轮流选矩阵中的 0 变为 1，已经冲突的不能选，问最后谁能赢
 * 输入 t，接着每个矩阵的大小 m，n，接着输入矩阵 m x n 个数
 * 输出 Ashish 或 Vivek
 */
import java.util.*;
import java.io.*;
public class CodeForceSolution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while(t-- > 0) {
            int m = sc.nextInt(), n = sc.nextInt();
            Set<Integer> rows = new HashSet<>();
            Set<Integer> cols = new HashSet<>();
            for(int i = 0;i < m;i++) {
                for(int j = 0;j < n;j++) {
                    int c = sc.nextInt();
                    if (c == 1) {
                        rows.add(i);
                        cols.add(j);
                    }
                }
            }
            int min = Math.min(m - rows.size(), n - cols.size());
            if (min % 2 == 0) {
                pw.println("Vivek");
            } else {
                pw.println("Ashish");
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
