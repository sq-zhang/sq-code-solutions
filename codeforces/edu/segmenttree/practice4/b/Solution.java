package codeforces.edu.segmenttree.practice4.b;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static Node[] t;
    static Node[] a;
    static int r, m, n;
    public static void main(String[] args) {
        r = sc.nextInt();
        n = sc.nextInt();
        m = sc.nextInt();
        a = new Node[n];
        for(int i = 0;i < n;i++) {
            a[i] = new Node(sc.nextInt(), sc.nextInt(),
                sc.nextInt(), sc.nextInt());
        }
        t = new Node[4 * n];
        build(1, 0, n - 1);
        while (m-- > 0) {
            int left = sc.nextInt(), right = sc.nextInt();
            Node res = query(1, 1, n, left, right);
            pw.println(res);
        }
        pw.flush();
    }

    static Node combineNode(Node n1, Node n2) {
        int a00 = n1.a00 * n2.a00 + n1.a01 * n2.a10;
        int a01 = n1.a00 * n2.a01 + n1.a01 * n2.a11;
        int a10 = n1.a10 * n2.a00 + n1.a11 * n2.a10;
        int a11 = n1.a10 * n2.a01 + n1.a11 * n2.a11;
        return new Node(a00 % r, a01 % r, a10 % r, a11 % r);
    }

    static void build(int p, int l, int r) {
        if (l == r) {
            t[p] = a[l];
        } else {
            int mid = (l + r) / 2;
            build(p * 2, l, mid);
            build(p * 2 + 1, mid + 1, r);
            t[p] = combineNode(t[p * 2], t[p * 2 + 1]);
        }
    }

    static Node query(int p, int tl, int tr, int l, int r) {
        if (l > r) {
            return new Node(1, 0, 0, 1);
        }
        if (tl == l && r == tr) {
            return t[p];
        }
        int mid = (tl + tr) / 2;
        Node left = query(p * 2, tl, mid, l, Math.min(r, mid));
        Node right = query(p * 2 + 1, mid + 1, tr, Math.max(l, mid + 1), r);
        return combineNode(left, right);
    }

    static class Node {
        int a00, a01, a10, a11;
        public Node(int a00, int a01, int a10, int a11) {
            this.a00 = a00;
            this.a01 = a01;
            this.a10 = a10;
            this.a11 = a11;
        }

        @Override
        public String toString() {
            return a00 + " " + a01 + "\n"
                + a10 + " " + a11 + "\n";
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