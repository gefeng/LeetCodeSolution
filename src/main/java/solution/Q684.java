package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Redundant Connection",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/redundant-connection/"
)
public class Q684 {
    public int[] findRedundantConnection(int[][] edges) {
        return dfsSolution(edges);
    }

    private int[] dfsSolution(int[][] edges) {
        int[] edgeToRemove = new int[2];
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for(int i = 0; i < edges.length; i++) {
            int[] e = edges[i];
            if(dfs(graph, e[1], e[0], new HashSet<>())) {
                edgeToRemove[0] = e[0];
                edgeToRemove[1] = e[1];
                break;
            }

            if(!graph.containsKey(e[0]))
                graph.put(e[0], new ArrayList<>());
            if(!graph.containsKey(e[1]))
                graph.put(e[1], new ArrayList<>());
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }

        return edgeToRemove;
    }

    private boolean dfs(HashMap<Integer, List<Integer>> graph, int target, int v, Set<Integer> visited) {
        if(v == target)
            return true;

        visited.add(v);

        if(graph.containsKey(v)) {
            for(int n : graph.get(v)) {
                if(visited.contains(n))
                    continue;
                if(dfs(graph, target, n, visited))
                    return true;
            }
        }
        return false;
    }

    private int[] unionFindSolution(int[][] edges) {
        int[] edgeToRemove = new int[2];
        int[] parent = new int[edges.length + 1];
        for(int i = 0; i < parent.length; i++)
            parent[i] = i;

        for(int i = 0; i < edges.length; i++) {
            int[] e = edges[i];
            int setA = find(parent, e[0]);
            int setB = find(parent, e[1]);
            if(setA == setB) {
                edgeToRemove = e;
                break;
            }
            // union
            parent[setA] = setB;
        }

        return edgeToRemove;
    }

    private int find(int[] parent, int i) {
        if(parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }
}
