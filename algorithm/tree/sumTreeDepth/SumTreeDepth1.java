package algorithm.tree.sumTreeDepth;

/*
 * 输入一个二叉树，求所有节点的深度和
 * 一个节点的深度为它到根节点的距离
 * */
public class SumTreeDepth1 {

    static class TreeNode {
        TreeNode left;
        TreeNode right;
    }

    int res;

    void dfs(TreeNode root, int depth) {
        if (root == null) {
            return;
        }
        depth++;
        dfs(root.left, depth);
        dfs(root.right, depth);
        res += depth;
    }

    int sumTreeDepth(TreeNode root) {
        res = 0;
        dfs(root, 0);
        return res;
    }
}
