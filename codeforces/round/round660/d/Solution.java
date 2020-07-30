package codeforces.round.round660.d;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.*;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        int[] a = sc.nextArray(n);
        int[] b = sc.nextArray(n);
        Node[] c = new Node[n];
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> {
            if (o1.b < 0) {
                return 1;
            }
            if (o2.b < 0) {
                return -1;
            }
            return o2.a - o1.a;
        });
        for (int i = 0;i < n;i++) {
            c[i] = new Node(a[i], b[i], i);
        }
        for (int i = 0;i < n;i++) {
            if (c[i].b > 0) {
                c[i].prev = c[c[i].b - 1];
            }
            pq.add(c[i]);
        }
        long ans = 0;
        List<Integer> wait = new ArrayList<>();
        List<Integer> now = new ArrayList<>();
        for(int i = 0;i < n;i++) {
            Node node = pq.poll();
            if (node == null) {
                continue;
            }
            ans += node.a;
            if (node.a > 0) {
                now.add(node.i);
                if (node.prev != null) {
                    node.prev.a += node.a;
                }
            } else {
                wait.add(node.i);
            }
        }
        pw.println(ans);
        for (Integer num : now) {
            pw.print((num + 1) + " ");
        }
        for (Integer num : wait) {
            pw.print((num + 1) + " ");
        }
        pw.println();
        pw.flush();
    }

    static class Node {
        int a, b, i;
        Node prev;
        public Node(int a, int b, int i) {
            this.a = a;
            this.b = b;
            this.i = i;
            this.prev = null;
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