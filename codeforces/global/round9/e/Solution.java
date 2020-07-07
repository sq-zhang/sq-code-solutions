package codeforces.global.round9.e;

import java.util.ArrayList;
import java.util.List;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        List<int[]> invs = new ArrayList<>();
        int[] nums = sc.nextArray(n);
        for(int i = 0;i < n;++i) {
            for(int j = i + 1;j < n;++j) {
                if(nums[i] > nums[j]) {
                    invs.add(new int[] {i, j});
                }
            }
        }
        invs.sort((a, b) -> {
            if(a[0] != b[0]) {
                return a[0] - b[0];
            }
            if(nums[a[1]] != nums[b[1]]) {
                return nums[b[1]] - nums[a[1]];
            }
            return b[1] - a[1];
        });
        pw.println(invs.size());
        for(int[] p : invs) {
            pw.println((p[0] + 1) + " " + (p[1] + 1));
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