package codeforces.round.edu92.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            char[] a = sc.next().toCharArray();
            int[][] count = new int[10][2];
            for(int i = 0;i < 10;i++) {
                count[i][0] = i;
            }
            for (char c : a) {
                count[c - '0'][1]++;
            }
            Arrays.sort(count, ((o1, o2) -> o2[1] - o1[1]));
            int reserve;
            if (count[0][1] > count[1][1] * 2) {
                reserve = count[0][1];
            } else if (count[0][1] == count[1][1]){
                reserve = count[0][1] * 2;
            } else {
                reserve = count[1][1] * 2;
            }
            pw.println(a.length - reserve);
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