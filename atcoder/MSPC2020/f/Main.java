package atcoder.MSPC2020.f;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    static final FS sc = new FS();  // 封装输入类
    static final PrintWriter pw = new PrintWriter(System.out);

    public static void main(String[] args) {
        int n = sc.nextInt();
        List<Node> up = new ArrayList<>();
        List<Node> left = new ArrayList<>();
        for (int i = 0;i < n;i++) {
            int x = sc.nextInt(), y = sc.nextInt();
            String d = sc.next();
            switch (d) {
                case "U":
                    up.add(new Node(x, y, 1));
                    break;
                case "D":
                    up.add(new Node(x, y, -1));
                    break;
                case "L":
                    left.add(new Node(x, y, 1));
                    break;
                default:
                    left.add(new Node(x, y, -1));
                    break;
            }
        }

        double ans = Double.MAX_VALUE;
        // up down
        for(int i = 0;i < up.size();i++) {
            Node n1 = up.get(i);
            for(int j = i + 1;j < up.size();j++) {
                Node n2 = up.get(j);
                if (n1.x != n2.x || n1.d == n2.d) {
                    continue;
                }
                if (n1.d == -1 && n1.y > n2.y) {
                    ans = Math.min(ans, (n1.y - n2.y) / 2.0);
                } else if (n1.d == 1 && n2.y > n1.y) {
                    ans = Math.min(ans, (n2.y - n1.y) / 2.0);
                }
            }
        }

        // left right
        for(int i = 0;i < left.size();i++) {
            Node n1 = left.get(i);
            for(int j = i + 1;j < left.size();j++) {
                Node n2 = left.get(j);
                if (n1.y != n2.y || n1.d == n2.d) {
                    continue;
                }
                if (n1.d == -1 && n1.x < n2.x) {
                    ans = Math.min(ans, (n1.x - n2.x) / 2.0);
                } else if (n1.d == 1 && n2.x < n1.x) {
                    ans = Math.min(ans, (n2.x - n1.x) / 2.0);
                }
            }
        }

        // left right and up down
        for (Node n1 : up) {
            for (Node n2 : left) {
                int dx = Math.abs(n1.x - n2.x);
                int dy = Math.abs(n1.y - n2.y);
                if (dx != dy) {
                    continue;
                }
                if (n1.d == 1) {
                    if (n2.d == 1) {
                        if (n1.y < n2.y && n1.x < n2.x) {
                            ans = Math.min(ans, dx);
                        }
                    } else {
                        if (n1.y < n2.y && n1.x > n2.x) {
                            ans = Math.min(ans, dx);
                        }
                    }
                } else {
                    if (n2.d == 1) {
                        if (n1.y > n2.y && n1.x < n2.x) {
                            ans = Math.min(ans, dx);
                        }
                    } else {
                        if (n1.y > n2.y && n1.x > n2.x) {
                            ans = Math.min(ans, dx);
                        }
                    }
                }
            }
        }
        if (ans == Double.MAX_VALUE) {
            pw.println("SAFE");
        } else {
            pw.println((long) ans * 10);
        }
        pw.flush();
    }

    static class Node {
        int d, x, y;
        public Node(int x, int y, int d) {
            this.x = x;
            this.y = y;
            this.d = d;
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