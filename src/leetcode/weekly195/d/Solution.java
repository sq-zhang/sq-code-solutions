package leetcode.weekly195.d;

import com.sun.tools.javac.util.Assert;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * @author sqzhang
 * @date 2020/6/22
 */
class Solution {
    static class Node {
        int idx, from, to, w;
        Node(int idx, int from, int to, int w) {
            this.idx = idx;
            this.from = from;
            this.to = to;
            this.w = w;
        }
    }

    private int[] root; // 并查集
    private int n, m; // n 节点数 m 边数
    private List<Node> nodes;

    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        this.n = n;
        this.m = edges.length;
        nodes = new ArrayList<>();
        for(int i = 0;i < m;i++) {
            nodes.add(new Node(i, edges[i][0], edges[i][1], edges[i][2]));
        }
        nodes.sort(Comparator.comparingInt(a -> a.w));
        root = new int[n];
        Arrays.fill(root, -1);
        int minWeight = kruskal(-1, 0, 0);

        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        res.add(new ArrayList<>());
        for(int i = 0;i < m;i++) {
            Arrays.fill(root, -1);
            Node node = nodes.get(i);
            if (kruskal(i, 0, 0) > minWeight || find(node.from) != find(node.to)) {
                res.get(0).add(node.idx);
            } else {
                Arrays.fill(root, -1);
                union(node.from, node.to);
                if (kruskal(i, node.w, 1) == minWeight) {
                    res.get(1).add(node.idx);
                }
            }
        }
        return res;
    }

    private int kruskal(int skip, int weight, int count) {
        for(int i = 0;i < m;i++) {
            if (i == skip) {
                continue;
            }
            Node node = nodes.get(i);
            if (union(node.from, node.to)) {
                weight += node.w;
                if (++count == n - 1) {
                    break;
                }
            }
        }
        return weight;
    }

    private int find(int a) {
        if (root[a] < 0) {
            return a;
        }
        root[a] = find(root[a]);
        return root[a];
    }

    private boolean union(int a, int b) {
        int ra = find(a), rb = find(b);
        if (ra == rb) {
            return false;
        }
        if (root[ra] < root[rb]) {
            root[rb] += root[ra];
            root[ra] = rb;
        } else {
            root[ra] += root[rb];
            root[rb] = ra;
        }
        return true;
    }

    public static void test(int n, int[][] edges, int[][] t) {
        Solution solution = new Solution();
        List<List<Integer>> res = solution.findCriticalAndPseudoCriticalEdges(n, edges);
        System.out.println(res);
        System.out.println(Arrays.asList(t));
    }

    public static void main(String[] args) {
        test(4, new int[][]{{0,1,1},{1,2,1},{2,3,1},{0,3,1}}, new int[][]{{}, {0,1,2,3}});
        test(5, new int[][]{{0,1,1},{1,2,1},{2,3,2},{0,3,2},{0,4,3},{3,4,3},{1,4,6}}, new int[][]{{0,1},{2,3,4,5}});
    }

}
