package codeforces.edu.segmenttree.practice2.a;

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
        int n = sc.nextInt(), m = sc.nextInt();
        a = sc.nextArray(n);
        t = new Node[4 * n];
        build(1, 0, n - 1);
        pw.println(getSegmentMaximumSum());
        while (m-- > 0) {
            int i = sc.nextInt() + 1, v = sc.nextInt();
            update(1, 1, n, i, v);
            pw.println(getSegmentMaximumSum());
        }
        pw.flush();
    }

    static void build(int p, int l, int r) {
        if (l == r) {
            t[p] = makeNode(a[l]);
            return;
        }
        int mid = (l + r) / 2;
        build(p * 2, l, mid);
        build(p * 2 + 1, mid + 1, r);
        t[p] = combineNode(t[p * 2], t[p * 2 + 1]);
    }

    static void update(int p, int tl, int tr, int pos, int val) {
        if (tl == tr) {
            t[p] = makeNode(val);
            return;
        }
        int mid = (tl + tr) / 2;
        if (pos <= mid) {
            update(p * 2, tl, mid, pos, val);
        } else {
            update(p * 2 + 1, mid + 1, tr, pos, val);
        }
        t[p] = combineNode(t[p * 2], t[p * 2 + 1]);
    }

    static int getSegmentMaximumSum() {
        return Math.max(t[1].max, 0);
    }

    static Node combineNode(Node n1, Node n2) {
        int prefix = Math.max(n1.prefix, n1.sum + n2.prefix);
        int suffix = Math.max(n2.suffix, n2.sum + n1.suffix);
        int sum = n1.sum + n2.sum;
        int max = Math.max(n1.max, Math.max(n2.max, n1.suffix + n2.prefix));
        return new Node(max, sum, prefix, suffix);
    }

    static Node makeNode(int val) {
        return new Node(val);
    }

    static class Node {
        int max, sum, prefix, suffix;
        public Node(int val) {
            this.max = val;
            this.sum = val;
            this.prefix = val;
            this.suffix = val;
        }
        public Node(int max, int sum, int prefix, int suffix) {
            this.max = max;
            this.suffix = suffix;
            this.prefix = prefix;
            this.sum = sum;
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