package codeforces.round648.d;

import java.util.*;
import java.io.*;
/**
 * @author sqzhang
 * @date 2020/6/11
 * D. Solve The Maze
 * 题目描述：给定一个 n x m 矩阵，. 代表空地，#代表墙，G 表示好人，B 表示坏人，出口在 (n, m)
 * 你可以在空地上放墙，试问有没有一种方法使所有好人逃脱，所有坏人被困
 * 输入：t，n x m，接着 n x m 矩阵
 */
public class CodeForceSolution {
    static final FS sc = new FS();
    static final PrintWriter pw = new PrintWriter(System.out);
    static int m, n;

    static boolean valid(int x, int y) {
        return x >= 0 && y >= 0 && x < m && y < n;
    }

    static class Node {
        int x, y;
        Node(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) {
        int t = sc.nextInt();
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        while (t-- > 0) {
            m = sc.nextInt();
            n = sc.nextInt();
            char[][] matrix = new char[m][n];
            for(int i = 0;i < m;i++) {
                matrix[i] = sc.next().toCharArray();
            }

            for(int i = 0;i < m;i++) {
                for (int j = 0;j < n;j++) {
                    if (matrix[i][j] == 'B') {
                        for(int[] dir : dirs) {
                            int nx = i + dir[0], ny = j + dir[1];
                            if (valid(nx, ny) && matrix[nx][ny] == '.') {
                                matrix[nx][ny] = '#';
                            }
                        }
                    }
                }
            }

            boolean[][] visited = new boolean[m][n];
            Queue<Node> queue = new LinkedList<>();
            if (matrix[m - 1][n - 1] == '.') {
                queue.add(new Node(m - 1, n - 1));
                visited[m - 1][n - 1] = true;
            }
            while (!queue.isEmpty()) {
                Node node = queue.poll();
                for (int[] dir : dirs) {
                    int nx = node.x + dir[0], ny = node.y + dir[1];
                    if (valid(nx, ny) && !visited[nx][ny] && matrix[nx][ny] != '#') {
                        queue.add(new Node(nx, ny));
                        visited[nx][ny] = true;
                    }
                }
            }

            boolean res = true;
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    if ((matrix[i][j] == 'G' && !visited[i][j]) ||
                        (matrix[i][j] == 'B' && visited[i][j])) {
                        res = false;
                        break;
                    }
                }
            }
            pw.println(res ? "Yes" : "No");
        }
        pw.flush();
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
        int nextInt() {
            return Integer.parseInt(next());
        }
        long nextLong() {
            return Long.parseLong(next());
        }
    }
}