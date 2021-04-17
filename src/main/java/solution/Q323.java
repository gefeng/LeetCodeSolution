package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Problem(
        title = "Number of Connected Components in an Undirected Graph",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/number-of-connected-components-in-an-undirected-graph/"
)
public class Q323 {
    public int countComponents(int n, int[][] edges) {
        return dfsSolution(n, edges);
    }

    private int unionFindSolution(int n ,int[][] edges) {
        int count = 0;
        int[] parent = new int[n];
        for(int i = 0; i < n; i++)
            parent[i] = i;

        for(int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            int setA = find(parent, v1);
            int setB = find(parent, v2);
            if(setA != setB)
                union(parent, v1, v2);
        }

        for(int i = 0; i < n; i++) {
            if(parent[i] == i)
                count++;
        }
        return count;
    }

    private int find(int[] parent, int i) {
        if(parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }

    private void union(int[] parent, int i, int j) {
        parent[find(parent, i)] = find(parent, j);
    }

    private int dfsSolution(int n, int[][] edges) {
        HashMap<Integer, List<Integer>> graph = buildGraph(edges);
        int count = 0;
        HashSet<Integer> visited = new HashSet<>();
        for(int i = 0; i < n; i++) {
            if(visited.contains(i))
                continue;
            dfs(graph, i, visited);
            count++;
        }

        return count;
    }

    private void dfs(HashMap<Integer, List<Integer>> graph, int v, HashSet<Integer> visited) {
        visited.add(v);

        if(graph.containsKey(v)) {
            for(int neighbor : graph.get(v)) {
                if(visited.contains(neighbor))
                    continue;
                dfs(graph, neighbor, visited);
            }
        }
    }

    private HashMap<Integer, List<Integer>> buildGraph(int[][] edges) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            graph.putIfAbsent(v1, new ArrayList<>());
            graph.putIfAbsent(v2, new ArrayList<>());
            graph.get(v1).add(v2);
            graph.get(v2).add(v1);
        }
        return graph;
    }
}
