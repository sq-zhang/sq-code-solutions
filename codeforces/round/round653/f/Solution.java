package codeforces.round.round653.f;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);
    static List<Integer> res;

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt();
            int[] a = new int[n];
            for (int i = 0; i < n; i++) {
                a[i] = sc.nextInt();
            }
            solve(n, a);
            if (res == null) {
                pw.println(-1);
                continue;
            }
            pw.println(res.size());
            for (int x : res) {
                pw.print(x + 1 + " ");
            }
            pw.println();
        }

        pw.flush();
    }

    public static void solve(int n, int[] a) {
        res = new ArrayList<>();
        for (int i = 0; i < n - 2; i++) {
            int min = i;
            for (int j = i; j < n; j++) {
                if (a[min] > a[j]) {
                    min = j;
                }
            }
            while (min > i) {
                if (min - i == 1) {
                    shift(a, i);
                    min++;
                } else {
                    shift(a, min - 2);
                    min -= 2;
                }
            }
        }
        if (a[n - 2] <= a[n - 1]) {
            return;
        }
        if (a[n - 3] == a[n - 1]) {
            shift(a, n - 3);
            return;
        }
        int idx = -1;
        for (int i = 0; i < n - 1; i++) {
            if (a[i] == a[i + 1]) {
                idx = i;
                break;
            }
        }
        if (idx == -1) {
            res = null;
            return;
        }
        for (int i = 0; i < 4; i++) {
            shift(a, idx + i / 2);
        }
        for (int i = idx + 2; i < n - 2; i++) {
            for (int j = 0; j < 2; j++) {
                shift(a, i);
            }
        }
    }

    public static void shift(int[] a, int i) {
        int tmp = a[i + 2];
        a[i + 2] = a[i + 1];
        a[i + 1] = a[i];
        a[i] = tmp;
        res.add(i);
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