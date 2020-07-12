package atcoder.aising.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);
    static int maxN = (int)(2 * 1e5) + 1;
    static Integer[] pop = new Integer[maxN];

    private static int solveNaive(int k) {
        int answer = 0;
        while (k > 0) {
            k = k % Integer.bitCount(k);
            answer++;
        }
        return answer;
    }

    public static void main(String[] args) {
        int n = sc.nextInt();
        char[] cs = sc.next().toCharArray();
        int originalPopcount = 0;
        for (char c : cs) {
            originalPopcount += c - '0';
        }

        // mod[0]: incremented
        // mod[1]: decremented
        int[][] modCache = new int[2][n];
        modCache[0][n - 1] = 1 % (originalPopcount + 1);
        if (originalPopcount - 1 > 0) {
            modCache[1][n - 1] = 1 % (originalPopcount - 1);
        }
        for (int i = n - 2; i >= 0; i--) {
            modCache[0][i] = (2 * modCache[0][i + 1]) % (originalPopcount + 1);
            if (originalPopcount - 1 > 0) {
                modCache[1][i] = (2 * modCache[1][i + 1]) % (originalPopcount - 1);
            }
        }

        int[] mod = new int[2];
        for (int i = 0; i < n; i++) {
            mod[0] *= 2;
            mod[0] += cs[i] - '0';
            mod[0] %= (originalPopcount + 1);
            if (originalPopcount - 1 > 0) {
                mod[1] *= 2;
                mod[1] += cs[i] - '0';
                mod[1] %= (originalPopcount - 1);
            }
        }

        for (int i = 0; i < n; i++) {
            if (cs[i] == '1' && originalPopcount == 1) {
                pw.println(0);
            } else if (cs[i] == '1') {
                int v = mod[1] - modCache[1][i];
                if (v < 0) {
                    v += originalPopcount - 1;
                }
                pw.println(solveNaive(v) + 1);
            } else {
                int v = mod[0] + modCache[0][i];
                v %= (originalPopcount + 1);
                pw.println(solveNaive(v) + 1);
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