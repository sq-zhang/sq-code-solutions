package codeforces.icpc.practice.a;

import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = null;


    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream("sort.out")));
        int n = sc.nextInt();
        int[] nums = sc.nextArray(n);
        Arrays.sort(nums);
        for (int i = 0;i < n;i++) {
            pw.println(nums[i]);
        }
        pw.flush();
    }

    static class FS {
        BufferedReader br;
        StringTokenizer st = new StringTokenizer("");

        public FS() {
            try {
                br = new BufferedReader(new InputStreamReader(
                        new FileInputStream("/Users/sqzhang/Downloads/sort.in")));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }

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