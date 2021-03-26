package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Critical Connections in a Network",
        difficulty = QDifficulty.HARD,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/critical-connections-in-a-network/"
)
public class Q1192 {
    private List<List<Integer>> ans;
    private HashMap<Integer, List<Integer>> graph;
    private int[] rank;
    private int R;
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        ans = new ArrayList<>();
        graph = new HashMap<>();
        rank = new int[n];
        R = 1;

        for(List<Integer> edge : connections) {
            graph.putIfAbsent(edge.get(0), new ArrayList<>());
            graph.putIfAbsent(edge.get(1), new ArrayList<>());
            graph.get(edge.get(0)).add(edge.get(1));
            graph.get(edge.get(1)).add(edge.get(0));
        }

        dfs(0, -1);
        return ans;
    }

    private int dfs(int v, int parent) {
        if(rank[v] != 0)
            return rank[v];

        rank[v] = R++;

        int minRank = Integer.MAX_VALUE;
        for(int neighbor : graph.get(v)) {
            if(neighbor == parent)
                continue;
            if(rank[neighbor] == 0)
                minRank = Math.min(minRank, dfs(neighbor, v));
            else
                minRank = Math.min(minRank, rank[neighbor]);
        }

        if(minRank >= rank[v] && parent != -1)
            ans.add(Arrays.asList(parent, v));
        return minRank;
    }
}
