package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Number of Operations to Make Network Connected",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/number-of-operations-to-make-network-connected/"
)
public class Q1319 {
    /**
     * Time:  O(E + V)
     * Space: O(E + V)
     * */
    public int makeConnected(int n, int[][] connections) {
        return unionFindSolution(n, connections);
    }

    private int dfsSolution(int n, int[][] connections) {
        if(connections.length < n - 1)
            return -1;

        int connectedGroups = 0;
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for(int i = 0; i < connections.length; i++) {
            int u = connections[i][0];
            int v = connections[i][1];
            if(!graph.containsKey(u))
                graph.put(u, new ArrayList<>());
            if(!graph.containsKey(v))
                graph.put(v, new ArrayList<>());
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                dfsConnected(graph, i, visited);
                connectedGroups++;
            }
        }


        return connectedGroups - 1;
    }


    private void dfsConnected(HashMap<Integer, List<Integer>> graph, int v, boolean[] visited) {
        visited[v] = true;
        if(graph.containsKey(v)) {
            for(int neighbor : graph.get(v))
                if(!visited[neighbor])
                    dfsConnected(graph, neighbor, visited);
        }
    }

    private int unionFindSolution(int n, int[][] connections) {
        if(connections.length < n - 1)
            return -1;

        int connectedGroups = 0;
        int[] parent = new int[n];
        int[] rank = new int[n];
        for(int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 1;
        }

        for(int i = 0; i < connections.length; i++) {
            int[] connection = connections[i];
            int groupA = find(parent, connection[0]);
            int groupB = find(parent, connection[1]);
            if(groupA != groupB) {
                if(rank[groupA] > rank[groupB]) {
                    rank[groupA] += rank[groupB];
                    parent[groupB] = groupA;
                } else {
                    rank[groupB] += rank[groupA];
                    parent[groupA] = groupB;
                }
                parent[groupA] = groupB;
            }
        }
        for(int i = 0; i < parent.length; i++) {
            if(parent[i] == i)
                connectedGroups++;
        }

        return connectedGroups - 1;
    }

    private int find(int[] parent, int i) {
        if(parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }
}
