package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Degree of a Connected Trio in a Graph",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/minimum-degree-of-a-connected-trio-in-a-graph/"
)
public class Q1761 {
    /*
    * graph的题如果要检查两个node是否connected，可以考虑adjacency matrix.
    * query 2d array is much faster than set/list/map
    * */
    public int minTrioDegree(int n, int[][] edges) {
        int minDegree = Integer.MAX_VALUE;
        boolean[][] graph = new boolean[n + 1][n + 1];
        int[] degree = new int[n + 1];

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            graph[u][v] = true;
            graph[v][u] = true;
            degree[u]++;
            degree[v]++;
        }

        for(int i = 1; i <= n - 2; i++) {
            for(int j = i + 1; j <= n - 1; j++) {
                for(int k = j + 1; k <= n; k++) {
                    if(graph[i][j] && graph[i][k] && graph[j][k]) {
                        minDegree = Math.min(minDegree, degree[i] + degree[j] + degree[k] - 6);
                    }
                }
            }
        }

        return minDegree == Integer.MAX_VALUE ? -1 : minDegree;
    }
}
