package codeforces.global.round9.g;

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
        TreeNode[] nodes = new TreeNode[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new TreeNode();
        }
        for (int i = 0; i < n - 1; i++) {
            int u = sc.nextInt() - 1, v = sc.nextInt() - 1;
            nodes[u].children.add(nodes[v]);
            nodes[v].children.add(nodes[u]);
        }
        int left = 0, right = 0;
        dfs(nodes[0], null, 0);
        for (TreeNode node : nodes) {
            if ((node.depth & 1) == 0) {
                left++;
            } else {
                right++;
            }
        }
        pw.println(Math.min(left, right) - 1);
        pw.flush();
    }

    private static void dfs(TreeNode node, TreeNode parent, int depth) {
        node.depth = depth;
        for(TreeNode child : node.children) {
            if (child != parent) {
                dfs(child, node, depth + 1);
            }
        }
    }

    private static class TreeNode {
        List<TreeNode> children = new ArrayList<>();
        int depth;
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