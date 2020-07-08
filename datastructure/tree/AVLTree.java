package datastructure.tree;

import datastructure.tree.TreePrinter.PrintableNode;
import java.util.Random;

/**
 * @author sqzhang
 * @year 2020
 */
public class AVLTree<T extends Comparable<T>> {
    public class TreeNode implements PrintableNode {
        public int balanceFactor; // balance factor = rightHeight - leftHeight
        public T value;
        public int height; // subtree height
        public TreeNode left, right;

        public TreeNode(T value) {
            this.value = value;
        }

        @Override
        public PrintableNode getLeft() {
            return left;
        }
        @Override
        public PrintableNode getRight() {
            return right;
        }
        @Override
        public String getText() {
            return value.toString();
        }
    }

    public TreeNode root;

    private boolean contains(TreeNode node, T value) {
        if (node == null) return false;
        int cmp = value.compareTo(node.value);
        if (cmp < 0) return contains(node.left, value);
        if (cmp > 0) return contains(node.right, value);
        return true;
    }

    public void insert(T value) {
        if (value == null) return;
        if (!contains(root, value)) {
            root = insert(root, value);
        }
    }

    private TreeNode insert(TreeNode node, T value) {
        if (node == null) return new TreeNode(value);
        int cmp = value.compareTo(node.value);
        if (cmp < 0) {
            node.left = insert(node.left, value);
        } else {
            node.right = insert(node.right, value);
        }
        updateNodeHeightAndBalanceFactor(node);
        return reBalanceIfNeeded(node);
    }

    public void remove(T elem) {
        if (elem == null) return;

        if (contains(root, elem)) {
            root = remove(root, elem);
        }
    }

    private TreeNode remove(TreeNode node, T elem) {
        if (node == null) return null;
        int cmp = elem.compareTo(node.value);
        if (cmp < 0) {
            node.left = remove(node.left, elem);
        } else if (cmp > 0) {
            node.right = remove(node.right, elem);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                TreeNode nextNode = node;
                if (node.left.height > node.right.height) {
                    while (nextNode.right != null) {
                        nextNode = nextNode.right;
                    }
                    node.value = nextNode.value;
                    node.left = remove(node.left, nextNode.value);
                } else {
                    while (nextNode.left != null) {
                        nextNode = nextNode.left;
                    }
                    node.value = nextNode.value;
                    node.right = remove(node.right, nextNode.value);
                }
            }
        }
        updateNodeHeightAndBalanceFactor(node);
        return reBalanceIfNeeded(node);
    }

    private void updateNodeHeightAndBalanceFactor(TreeNode node) {
        int leftNodeHeight = (node.left == null) ? -1 : node.left.height;
        int rightNodeHeight = (node.right == null) ? -1 : node.right.height;

        node.height = 1 + Math.max(leftNodeHeight, rightNodeHeight);
        node.balanceFactor = rightNodeHeight - leftNodeHeight;
    }

    private TreeNode reBalanceIfNeeded(TreeNode node) {
        if (node.balanceFactor == -2) {
            if (node.left.balanceFactor <= 0) {
                return leftLeftCase(node);
            } else {
                return leftRightCase(node);
            }
        } else if (node.balanceFactor == 2) {
            if (node.right.balanceFactor >= 0) {
                return rightRightCase(node);
            } else {
                return rightLeftCase(node);
            }
        }
        return node;
    }

    private TreeNode leftLeftCase(TreeNode node) {
        return rightRotation(node);
    }

    private TreeNode leftRightCase(TreeNode node) {
        node.left = leftRotation(node.left);
        return leftLeftCase(node);
    }

    private TreeNode rightRightCase(TreeNode node) {
        return leftRotation(node);
    }

    private TreeNode rightLeftCase(TreeNode node) {
        node.right = rightRotation(node.right);
        return rightRightCase(node);
    }

    private TreeNode leftRotation(TreeNode node) {
        TreeNode newParent = node.right;
        node.right = newParent.left;
        newParent.left = node;
        this.updateNodeHeightAndBalanceFactor(node);
        this.updateNodeHeightAndBalanceFactor(newParent);
        return newParent;
    }

    private TreeNode rightRotation(TreeNode node) {
        TreeNode newParent = node.left;
        node.left = newParent.right;
        newParent.right = node;
        this.updateNodeHeightAndBalanceFactor(node);
        this.updateNodeHeightAndBalanceFactor(newParent);
        return newParent;
    }

    @Override
    public String toString() {
        return TreePrinter.getTreeDisplay(root);
    }

    public boolean validateBST(TreeNode node) {
        if (node == null) return true;
        T val = node.value;
        boolean isValid = true;
        if (node.left != null) isValid = node.left.value.compareTo(val) < 0;
        if (node.right != null) isValid = isValid && node.right.value.compareTo(val) > 0;
        return isValid && validateBST(node.left) && validateBST(node.right);
    }

    public static void main(String[] args) {
        AVLTree<Integer> tree = new AVLTree<>();
        Random rand = new Random();
        for(int i = 0;i < 20;i++) {
            tree.insert(rand.nextInt(100));
            System.out.println(tree);
        }
    }
}
