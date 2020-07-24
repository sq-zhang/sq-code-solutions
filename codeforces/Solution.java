package codeforces;

import java.util.*;
import java.io.*;
public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) throws IOException {
//        BufferedReader buffer = new BufferedReader(new InputStreamReader(System.in));
//        System.out.println("请输入一串字符串");
//        String text = buffer.readLine();
//        System.out.println("您输入的字符串是:" + text);

        Scanner scan = new Scanner(System.in);
        System.out.println("请输入一个字符串:");
        System.out.println("您输入的字符串是:" + scan.nextLine());
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