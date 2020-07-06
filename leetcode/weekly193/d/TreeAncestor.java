package leetcode.weekly193.d;

/**
 * @author sqzhang
 * @date 2020/6/14
 * 给你一棵树，树上有 n 个节点，按从 0 到 n-1 编号。树以父节点数组的形式给出，其中 parent[i] 是节点 i 的父节点。树的根节点是编号为 0 的节点。
 *
 * 请你设计并实现 getKthAncestor(int node, int k) 函数，函数返回节点 node 的第 k 个祖先节点。如果不存在这样的祖先节点，返回 -1 。
 *
 * 树节点的第 k 个祖先节点是从该节点到根节点路径上的第 k 个节点。
 *
 */
class TreeAncestor {
    private int maxN = (int) (1e5 + 10);
    private int[][] dp = new int[maxN][20];

    public TreeAncestor(int n, int[] parent) {
        for(int i = 0;i < n;i++) {
            dp[i + 1][0] = parent[i] + 1;
        }
        for(int i = 1;(1 << i) <= n;i++) {
            for(int u = 1;u <= n;u++) {
                dp[u][i] = dp[dp[u][i - 1]][i - 1];
            }
        }
    }

    public int getKthAncestor(int node, int k) {
        int u = node + 1;
        for(int i = 0;(1 << i) <= k;i++) {
            if (((1 << i) & k) != 0) {
                u = dp[u][i];
            }
        }
        return u - 1;
    }

    public static void main(String[] args) {
        TreeAncestor ancestor = new TreeAncestor(7, new int[]{-1,0,0,1,1,2,2});
        System.out.println(ancestor.getKthAncestor(3, 1));
        System.out.println(ancestor.getKthAncestor(5, 2));
        System.out.println(ancestor.getKthAncestor(6, 3));
    }
}