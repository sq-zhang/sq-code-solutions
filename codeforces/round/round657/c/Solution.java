package codeforces.round.round657.c;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Solution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int t = sc.nextInt();
        while (t-- > 0) {
            int n = sc.nextInt(), m = sc.nextInt();
            Node[] nodes = new Node[m];
            for(int i = 0;i < m ;i++) {
                nodes[i] = new Node(sc.nextInt(), sc.nextInt());
            }
            Arrays.sort(nodes, Comparator.comparingInt(o -> o.x));

            long[] suf = new long[m];
            suf[m - 1] = nodes[m - 1].x;
            for(int i = m - 2; i >= 0;i--) {
                suf[i] = suf[i + 1] + nodes[i].x;
            }
            long res = 0;
            if(n <= m) {
                res = suf[m - n];
            } else {
                res = suf[0];
            }

            for(int i = 0 ; i < m;i++) {
                long sum = 0, x = n - 1;
                sum += nodes[i].x;
                if(x > 0) {
                    int ind = binarySearch(nodes, nodes[i].y + 1);
                    if(ind == m) {
                        sum += x * nodes[i].y;
                    } else {
                        int elem = m - ind;
                        if(nodes[i].x > nodes[i].y) {
                            sum -= nodes[i].x;
                            x++;
                        }
                        if(x >= elem) {
                            sum += suf[ind];
                            x -= elem;
                            sum += x * nodes[i].y;
                        } else {
                            sum += suf[(int)(m - x)];
                        }
                    }
                }
                res = Math.max(res, sum);
            }
            pw.println(res);
        }
        pw.flush();
    }
    static int binarySearch(Node[] p, int val) {
        int n = p.length, l = 0, r = n - 1;
        int ans = n;
        while(l <= r) {
            int mid = (l + r)/2;
            if(p[mid].x >= val) {
                ans = mid;
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return ans;
    }

    static class Node {
        int x, y;
        public Node(int x, int y) {
            this.x = x;
            this.y = y;
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