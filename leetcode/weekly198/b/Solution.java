package leetcode.weekly198.b;

import java.util.*;

/**
 * @author sqzhang
 * @year 2020
 */
class Solution {
    int[] res;
    public int[] countSubTrees(int n, int[][] edges, String labels) {
        res = new int[n];
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0;i < n;i++) {
            adj.add(new ArrayList<>());
        }
        for(int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }
        char[] label = labels.toCharArray();
        dfs(adj, 0, label, new boolean[n]);
        System.out.println(Arrays.toString(res));
        return res;
    }

    private Map<Character, Integer> dfs(List<List<Integer>> adj, int cur, char[] label, boolean[] vis) {
        Map<Character, Integer> maps = new HashMap<>();
        maps.put(label[cur], 1);
        vis[cur] = true;
        for (Integer child : adj.get(cur)) {
            if (vis[child]) {
                continue;
            }
            Map<Character, Integer> left = dfs(adj, child, label, vis);
            for(Character c : left.keySet()) {
                maps.put(c, maps.getOrDefault(c, 0) + left.get(c));
            }
        }
        res[cur] = maps.get(label[cur]);
        return maps;
    }

    public static void main(String[] args) {
        int n = 7;
        int[][] edges = {{0,1},{1,2},{2,3},{3,4},{4,5},{5,6}};
        String labesl = "aaabaaa";
        Solution solution = new Solution();
        solution.countSubTrees(n, edges,labesl);
    }
}