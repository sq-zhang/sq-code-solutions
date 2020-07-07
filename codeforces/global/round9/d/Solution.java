package codeforces.global.round9.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = sc.nextArray(n);
            StringBuilder res = new StringBuilder();
            int count = 0;
            while (!isSorted(a)) {
                int mex = 0;
                boolean[] seen = new boolean[n+1];
                for(int i = 0; i < n; i++){
                    seen[a[i]] = true;
                    while(seen[mex]) {
                        mex++;
                    }
                }
                if (mex != n) {
                    a[mex] = mex;
                    res.append(mex + 1).append(" ");
                } else {
                    for(int i = 0; i < n; i++){
                        if(a[i] != i){
                            a[i] = mex;
                            res.append(i + 1).append(" ");
                            break;
                        }
                    }
                }
                count++;
            }
            pw.println(count);
            pw.println(res.toString());
        }
        pw.flush();
    }

    private static boolean isSorted(int[] a) {
        for(int i = 0;i < a.length - 1;i++) {
            if (a[i] > a[i + 1]) {
                return false;
            }
        }
        return true;
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