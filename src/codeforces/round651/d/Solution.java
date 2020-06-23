package codeforces.round651.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static  int n, k;
    static  int[] nums;
    public static void main(String[] args) {
        n = sc.nextInt();
        k = sc.nextInt();
        nums = new int[n];
        for(int i = 0;i < n;i++) {
            nums[i] = sc.nextInt();
        }
        int l = 1, r = (int) 10e9;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (search(mid, 0) || search(mid, 1)) {
                r = mid;
            } else {
                l = mid + 1;
            }
        }
        pw.println(l);
        pw.flush();
    }

    private static boolean search(int mid, int pos) {
        int count = 0;
        for(int i = 0;i < n;i++) {
            if (pos == 0) {
                count++;
                pos ^= 1;
            } else if (nums[i] <= mid) {
                count++;
                pos ^= 1;
            }
        }
        return count >= k;
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