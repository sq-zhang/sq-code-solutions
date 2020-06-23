package algorithm.tree.sumTreeDepth;

/*
 * 输入一个二叉树，求根节点的子树深度和
 * 一个节点的深度为它到根节点的距离
 * 一个节点的子树深度和为子节点的深度和
 *
 *            1
 *          /    \
 *         2      3
 *        /  \   /  \
 *       4    5  6   7
 *     /   \
 *    8     9
 *
 *  ans = 26
 *  8, 9, 5, 6, 7 : (1, 0)
 *  3, 4: (1, 0) -> (2, 1) -> (3, 2)
 *  2: (1, 0) -> (4, 5) -> (5, 6)
 *  1: (1, 0) -> (6, 11) -> (9, 16)
 *  ans = 4 + 6 + 16 = 26;
 * */
public class SumTreeDepth2 {

    static class TreeNode {
        TreeNode left;
        TreeNode right;
    }

    static class Result {
        int x, y;
        Result(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    int ans = 0;

    Result dfs(TreeNode root) {
        Result res = new Result(1, 0);
        if (root.left != null) {
            Result resChild = dfs(root.left);
            res.x += resChild.x;
            res.y += resChild.x + resChild.y;
        }
        if (root.right != null){
            Result resChild = dfs(root.right);
            res.x += resChild.x;
            res.y += resChild.x + resChild.y;
        }
        ans += res.y;
        return res;
    }

    int sumTreeDepth(TreeNode root) {
        dfs(root);
        return ans;
    }
}
