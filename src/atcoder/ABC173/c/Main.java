package atcoder.ABC173.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt(), m = sc.nextInt(), k = sc.nextInt();
        char[][] s = new char[n][m];
        for(int i=0; i < s.length; i++){
            String t = sc.next();
            s[i] = t.toCharArray();
        }

        int res = 0;
        for(int i = 0; i < 1 << n; i++){
            for(int j = 0; j < 1 << m; j++){
                int count = 0;
                for(int c = 0; c < n; c++){
                    for(int d = 0; d < m; d++){
                        if(((i >> c & 1) == 1) && ((j >> d & 1) == 1)){
                            if(s[c][d] == '#'){
                                count++;
                            }
                        }
                    }
                }
                if(count == k){
                    res++;
                }
            }
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