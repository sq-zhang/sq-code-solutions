package codeforces.icpc.practice.b;

import java.io.*;
import java.util.*;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = null;
    static int[] root;
    static int[] weights;
    public static void main(String[] args) throws FileNotFoundException {
        String path = "./src/codeforces/icpc/practice/b/b1.out";
        PrintWriter pw = new PrintWriter(new OutputStreamWriter(new FileOutputStream(path)));
        int n = sc.nextInt(), m = sc.nextInt();
        root = new int[n + 1];
        weights = new int[n + 1];
        Arrays.fill(weights, 1);
        for(int i = 0;i < m;i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            union(x, y);
        }

        Set<Integer> nums = new HashSet<>();
        for (int i = 1;i <= n;i++) {
            nums.add(find(i));
        }
        pw.println(nums.size());
        for (int num : nums) {
            pw.print(num + " ");
        }
        pw.println();
        pw.flush();
    }

    private static int find(int x) {
        while (root[x] > 0) {
            x = root[x];
        }
        return x;
    }
    private static void union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) {
            return;
        }
        if (weights[ra] < weights[rb]) {
            weights[rb] += weights[ra];
            root[ra] = rb;
        } else {
            weights[ra] += weights[rb];
            root[rb] = ra;
        }
    }

    static class FS {
        BufferedReader br;
        StringTokenizer st = new StringTokenizer("");

        public FS() {
            try {
                br = new BufferedReader(new InputStreamReader(
                        new FileInputStream("/Users/sqzhang/Downloads/mis/b1.in")));
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