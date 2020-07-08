package codeforces.problemset.segmenttree.round242e;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

// https://codeforces.com/problemset/problem/242/E
public class Solution {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);
    static int n, m;
    static long[] arr;
    public static void main(String[] args) {
        n = sc.nextInt();
        arr = new long[n + 1];
        for(int i = 1;i <= n;i++) {
            arr[i] = sc.nextLong();
        }
        SegmentTree[] st = new SegmentTree[20];
        for(int i = 0;i < 20;i++) {
            st[i] = new SegmentTree(n, i);
        }
        int t = sc.nextInt();
        while(t-- > 0) {
            int type = sc.nextInt();
            int x = sc.nextInt(), y = sc.nextInt();
            if(type == 1) {
                long ans = 0;
                for(int i = 0;i < 20;i++) {
                    ans += st[i].queryRange(1,1, n, x, y) * (1 << i);
                }
                pw.println(ans);
            } else {
                int val = sc.nextInt();
                for(int i = 0;i < 20;i++) {
                    if(((val >> i) & 1) == 1) {
                        st[i].updateRange(1,1, n, x, y);
                    }
                }
            }
        }
        pw.close();
    }

    static class SegmentTree{
        long[] tree;
        long[] lazy;
        int id;
        public SegmentTree(int n, int i) {
            tree = new long[4 * n];
            lazy = new long[4 * n];
            id = i;
            build(1, 1, n);
        }

        void build(int node, int start, int end) {
            if (start == end) {
                // Leaf node will have a single element
                tree[node] = (arr[start] >> id) & 1;
            } else {
                int mid = (start + end) / 2;
                // Recurse on the left child
                build(2 * node, start, mid);
                // Recurse on the right child
                build(2 * node + 1, mid + 1, end);
                // internal node will have the sum of both of its children
                tree[node] = tree[2 * node] + tree[2 * node + 1];
            }
        }

        void updateRange(int node, int start, int end, int l, int r) {
            if (lazy[node] != 0) {
                // This node needs to be updated
                tree[node] = (end - start + 1) - tree[node]; // Update it
                if (start != end) {
                    lazy[node * 2] ^= lazy[node]; // Mark child as lazy
                    lazy[node * 2 + 1] ^= lazy[node]; // Mark child as lazy
                }
                lazy[node] = 0; // Reset it
            }
            if (start > end || start > r || end < l) // Current segment is not within range [l, r]
                return;
            if (start >= l && end <= r) {      // Segment is fully within range
                tree[node] = (end - start + 1) - tree[node];
                if (start != end) {
                    // Not leaf node
                    lazy[node * 2] ^= 1;
                    lazy[node * 2 + 1] ^= 1;
                }
                return;
            }
            int mid = (start + end) / 2;
            updateRange(node * 2, start, mid, l, r); // Updating left child
            updateRange(node * 2 + 1, mid + 1, end, l, r); // Updating right child
            tree[node] = tree[node * 2] + tree[node * 2 + 1]; // Updating root with max value
        }

        long queryRange(int node, int start, int end, int l, int r) {
            if (start > end || start > r || end < l)
                return 0; // Out of range
            if (lazy[node] != 0) {
                tree[node] = (end - start + 1) - tree[node]; // This node needs to be updated Update it
                if (start != end) {
                    lazy[node * 2] ^= lazy[node]; // Mark child as lazy
                    lazy[node * 2 + 1] ^= lazy[node]; // Mark child as lazy
                }
                lazy[node] = 0; // Reset it
            }
            if (start >= l && end <= r) // Current segment is totally within range [l, r]
                return tree[node];
            int mid = (start + end) / 2;
            long p1 = queryRange(node * 2, start, mid, l, r); // Query left child
            long p2 = queryRange(node * 2 + 1, mid + 1, end, l, r); // Query right child

            return (p1 + p2);
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