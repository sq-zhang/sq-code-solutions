package datastructure.tree;

import java.util.*;

/**
 * binary search tree
 * @author sqzhang
 * @year 2020
 */
public class BinarySearchTree<T extends Comparable<T>> {
    private int nodeCount = 0;
    private Node root = null;

    private class Node {
        T data;
        Node left, right;
        public Node(Node left, Node right, T data) {
            this.data = data;
            this.left = left;
            this.right = right;
        }
    }

    public int size() {
        return nodeCount;
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public boolean add(T data) {
        if (contains(data)) {
            return false;
        } else {
            root = add(root, data);
            nodeCount++;
            return true;
        }
    }

    private Node add(Node node, T data) {
        if (node == null) {
            node = new Node(null, null, data);
        } else {
            if (data.compareTo(node.data) < 0) {
                node.left = add(node.left, data);
            } else {
                node.right = add(node.right, data);
            }
        }
        return node;
    }

    public boolean remove(T elem) {

        // Make sure the node we want to remove
        // actually exists before we remove it
        if (contains(elem)) {
            root = remove(root, elem);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node remove(Node node, T data) {
        if (node == null) {
            return null;
        }
        int cmp = data.compareTo(node.data);
        if (cmp < 0) {
            node.left = remove(node.left, data);
        } else if (cmp > 0) {
            node.right = remove(node.right, data);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            } else {
                // one way is put right min data as new parent
                Node rightMin = findMin(node.right);
                node.data = rightMin.data;
                node.right = remove(node.right, rightMin.data);

                // another way is put left max data be new parent
                //TreeNode leftMax = findMax(node.left);
                //node.data = leftMax.data;
                //node.left = remove(node.right, leftMax.data);
            }
        }
        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    private Node findMax(Node node) {
        while (node.right != null) {
            node = node.right;
        }
        return node;
    }

    public boolean contains(T elem) {
        return contains(root, elem);
    }

    private boolean contains(Node node, T elem) {
        if (node == null) return false;
        int cmp = elem.compareTo(node.data);
        if (cmp < 0) {
            return contains(node.left, elem);
        } else if (cmp > 0) {
            return contains(node.right, elem);
        } else {
            return true;
        }
    }

    public int height() {
        return height(root);
    }

    private int height(Node node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public enum TraversalOrder {
        PRE_ORDER, IN_ORDER, POST_ORDER, LEVEL_ORDER
    }

    public Iterator<T> traverse(TraversalOrder order) {
        switch (order) {
            case PRE_ORDER:     return preOrder();
            case IN_ORDER:      return inOrder();
            case POST_ORDER:    return postOrder();
            case LEVEL_ORDER:   return levelOrder();
            default: throw new UnsupportedOperationException();
        }
    }

    private Iterator<T> preOrder() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node> stack = new Stack<>();
        stack.push(root);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                return root != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                Node node = stack.pop();
                if (node.right != null) {
                    stack.push(node.right);
                }
                if (node.left != null) {
                    stack.push(node.left);
                }
                return node.data;
            }
        };
    }

    private Iterator<T> inOrder() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node> stack = new Stack<>();
        return new Iterator<T>() {
            Node cur = root;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                return cur != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                while (cur != null) {
                    stack.push(cur);
                    cur = cur.left;
                }
                Node node = stack.pop();
                cur = node.right;
                return node.data;
            }
        };
    }

    private Iterator<T> postOrder() {
        final int expectedNodeCount = nodeCount;
        final Stack<Node> stack1 = new Stack<>();
        final Stack<Node> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            Node node = stack1.pop();
            if (node != null) {
                stack2.push(node);
                if (node.left != null) stack1.push(node.left);
                if (node.right != null) stack1.push(node.right);
            }
        }
        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                return root != null && !stack2.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                return stack2.pop().data;
            }
        };
    }

    private Iterator<T> levelOrder() {

        final int expectedNodeCount = nodeCount;
        final Queue<Node> queue = new LinkedList<>();
        if (root != null) {
            queue.offer(root);
        }

        return new java.util.Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                return root != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount) {
                    throw new ConcurrentModificationException();
                }
                Node node = queue.poll();
                if (node == null) {
                    return null;
                }
                if (node.left != null) {
                    queue.offer(node.left);
                }
                if (node.right != null) {
                    queue.offer(node.right);
                }
                return node.data;
            }
        };
    }
}
