package algorithm.graph;

/**
 * @author sqzhang
 * @date 2020/5/28
 */
public class Dijkstra {
    public static int max = Integer.MAX_VALUE;

    public static void shortestPath(int[][] graph) {
        int n = graph.length;
        int[] path = new int[n];
        System.arraycopy(graph[0], 0, path, 0, n);
        boolean[] visited = new boolean[n];
        visited[0] = true;
        for(int i = 1;i < n;i++) {
            int min = max, minIndex = -1;
            for(int j = 1;j < n;j++) {
                if (!visited[j] && path[j] < min) {
                    min = path[j];
                    minIndex = j;
                }
            }
            if (minIndex == -1) {
                continue;
            }
            visited[minIndex] = true;
            for(int k = 1;k < n;k++) {
                if (!visited[k] && (min + graph[minIndex][k] < path[k])) {
                    path[k] = min + graph[minIndex][k];
                }
            }
        }
        for(int i = 1;i < n;i++) {
            System.out.println("0 -> " + i + ": " + path[i]);
        }
    }

    public static void main(String[] args) {
        int[][] graph = {{0, 6, 2, max}, {max, 0, max, 1}, {max, 3, max, 5}, {max,max,max,max}};
        shortestPath(graph);
    }
}
