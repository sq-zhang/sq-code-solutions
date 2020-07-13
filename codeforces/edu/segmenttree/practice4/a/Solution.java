package codeforces.edu.segmenttree.practice4.a;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static Node[] t;
    static int[] a;
    public static void main(String[] args) {
        int n = sc.nextInt();
        a = sc.nextArray(n);
        t = new Node[4 * n];
        build(1, 0, n - 1);
        int m = sc.nextInt();
        while (m-- > 0) {
            int k = sc.nextInt(), l = sc.nextInt(), r = sc.nextInt();
            if (k == 1) {
                pw.println(sum(1, 1, n, l, r).sum);
            } else {
                update(1, 1, n, l, r);
            }
        }
        pw.flush();
    }

    static Node makeNode(int val) {
        return new Node(val, 1);
    }

    static Node combineNode(Node n1, Node n2) {
        if (n1.count % 2 == 0) {
            return new Node(n1.sum + n2.sum, n1.count + n2.count);
        } else {
            return new Node(n1.sum - n2.sum, n1.count + n2.count);
        }
    }

    static void build(int p, int l, int r) {
        if (l == r) {
            t[p] = makeNode(a[l]);
        } else {
            int mid = (l + r) / 2;
            build(p * 2, l, mid);
            build(p * 2 + 1, mid + 1, r);
            t[p] = combineNode(t[p * 2], t[p * 2 + 1]);
        }
    }

    static void update(int p, int tl, int tr, int pos, int val) {
        if (tl == tr) {
            t[p] = makeNode(val);
            return;
        }
        int mid = (tl + tr) / 2;
        if(pos <= mid) {
            update(p * 2, tl, mid, pos, val);
        } else {
            update(p * 2 + 1, mid + 1, tr, pos, val);
        }
        t[p] = combineNode(t[p * 2], t[p * 2 + 1]);
    }

    static Node sum(int p, int tl, int tr, int l, int r) {
        if (l > r) {
            return new Node(0, 0);
        }
        if (tl == l && r == tr) {
            return t[p];
        }
        int mid = (tl + tr) / 2;
        Node left = sum(p * 2, tl, mid, l, Math.min(r, mid));
        Node right = sum(p * 2 + 1, mid + 1, tr, Math.max(l, mid + 1), r);
        return combineNode(left, right);
    }

    static class Node {
        int sum, count;
        public Node(int sum, int count) {
            this.sum = sum;
            this.count = count;
        }
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