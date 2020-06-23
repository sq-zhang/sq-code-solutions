package algorithm.tree.sumTreeDepth;

/*
 * 输入一个二叉树，求所有节点 target 节点的距离和
 *
 *            1
 *          /    \
 *         2      3
 *        /  \   /  \
 *       4    5  6   7
 *     /   \
 *    8     9
 *
 * */
public class SumTreeDepth3 {

    static class TreeNode {
        TreeNode left;
        TreeNode right;
        int size;
    }

    static class Pair {
        int x, y;
        Pair(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int ans;
    int n;

    Pair dfs(TreeNode root) {
        Pair p = new Pair(1, 0);
        if (root.left != null) {
            Pair pChild = dfs(root.left);
            p.x += pChild.x;
            p.y += pChild.x + pChild.y;
        }
        if (root.right != null){
            Pair pChild = dfs(root.right);
            p.x += pChild.x;
            p.y += pChild.x + pChild.y;
        }
        root.size = p.x;
        return p;
    }

    void dfs2(TreeNode root, int sumDist, TreeNode target) {
        if (root == target) {
            ans = sumDist;
        }
        if (root.left != null) {
            int newSum = sumDist - root.left.size + (n - root.left.size);
            dfs2(root.left, newSum, target);
        }
        if (root.right != null) {
            int newSum = sumDist - root.right.size + (n - root.right.size);
            dfs2(root.right, newSum, target);
        }
    }

    int sumTreeDepth(TreeNode root, TreeNode target) {
        ans = 0;
        Pair p = dfs(root);
        n = p.x;
        dfs2(root, p.y, target);
        return ans;
    }
}
