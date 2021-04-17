package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Graph Valid Tree",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/graph-valid-tree/"
)
public class Q261 {
    private class DisjointSet {
        int[] parents;
        int[] weights;

        DisjointSet(int n) {
            parents = new int[n];
            weights = new int[n];

            for(int i = 0; i < n; i++)
                parents[i] = i;
        }

        int find(int x) {
            if(parents[x] != x)
                parents[x] = find(parents[x]);
            return parents[x];
        }

        void union(int x, int y) {
            int setA = find(x);
            int setB = find(y);
            if(setA != setB) {
                if(weights[setA] >= weights[setB]) {
                    parents[setB] = setA;
                    weights[setA]++;
                } else {
                    parents[setA] = setB;
                    weights[setB]++;
                }
            }
        }
    }
    public boolean validTree(int n, int[][] edges) {
        return unionFindSolution(n, edges);
    }

    private boolean dfsSolution(int n, int[][] edges) {
        if(edges.length != n - 1)
            return false;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] edge : edges) {
            List<Integer> n1 = graph.computeIfAbsent(edge[0], k -> new ArrayList<>());
            List<Integer> n2 = graph.computeIfAbsent(edge[1], k -> new ArrayList<>());
            n1.add(edge[1]);
            n2.add(edge[0]);
        }

        Set<Integer> seen = new HashSet<>();
        dfsTraverse(graph, 0, seen);

        return seen.size() == n;
    }

    private void dfsTraverse(Map<Integer, List<Integer>> graph, int curr, Set<Integer> seen) {
        seen.add(curr);

        List<Integer> neighbors = graph.get(curr);
        if(neighbors != null) {
            for(int neighbor : neighbors) {
                if(!seen.contains(neighbor))
                    dfsTraverse(graph, neighbor, seen);
            }
        }
    }

    private boolean unionFindSolution(int n, int[][] edges) {
        if(edges.length != n - 1)
            return false;

        DisjointSet ds = new DisjointSet(n);

        for(int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            if(ds.find(v1) != ds.find(v2)) {
                ds.union(v1, v2);
            }
            else {
                return false;
            }
        }

        return true;
    }
}
