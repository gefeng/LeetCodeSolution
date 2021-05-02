package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Is Graph Bipartite",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/is-graph-bipartite/"
)
public class Q785 {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n]; // 1:red, 2:blue

        for(int i = 0; i < n; i++) {
            if(colors[i] == 0) {
                if(!dfsIsBipartite(graph, i, colors, 1))
                    return false;
            }
        }

        return true;
    }

    private boolean dfsIsBipartite(int[][] graph, int v, int[] colors, int color) {
        colors[v] = color;

        for(int nei : graph[v]) {
            if(colors[nei] == color)
                return false;
            if(colors[nei] == 0) {
                if(!dfsIsBipartite(graph, nei, colors, color == 1 ? 2 : 1))
                    return false;
            }
        }

        return true;
    }
}
