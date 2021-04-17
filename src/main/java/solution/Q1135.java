package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Connecting Cities With Minimum Cost",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/connecting-cities-with-minimum-cost/"
)
public class Q1135 {
    /*
    * minimum spanning tree - undirected graph subset of edges connect all nodes with the minimum total weights
    * 可以用union find解
    * */
    public int minimumCost(int N, int[][] connections) {
        return unionFindSolution(N, connections);
    }

    private int bfsSolution(int N, int[][] connections) {
        HashMap<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] c : connections) {
            List<int[]> n1 = graph.computeIfAbsent(c[0], k -> new ArrayList<>());
            List<int[]> n2 = graph.computeIfAbsent(c[1], k -> new ArrayList<>());
            n1.add(new int[] {c[1], c[2]});
            n2.add(new int[] {c[0], c[2]});
        }

        int minCost = 0;
        Set<Integer> seen = new HashSet<>();
        Queue<int[]> pQueue = new PriorityQueue<>((a, b) -> (a[1] - b[1]));
        pQueue.offer(new int[] {1, 0});

        while(!pQueue.isEmpty()) {
            int[] node = pQueue.poll();

            if(!seen.contains(node[0])) {
                minCost += node[1];
                seen.add(node[0]);
            }

            List<int[]> neighbors = graph.get(node[0]);
            if(neighbors != null) {
                for(int[] neighbor : neighbors) {
                    if(!seen.contains(neighbor[0])) {
                        pQueue.offer(neighbor);
                    }
                }
            }
        }

        return seen.size() == N ? minCost : -1;
    }

    /*
    * Performing union find each time takes log*N ~ amortized O(1)
    * by using path compressing and weighted union
    * */
    private class DisjointSet {
        private int[] parents;
        private int[] weighted;
        DisjointSet(int n) {
            parents = new int[n + 1];
            weighted = new int[n + 1];
            for(int i = 0; i < parents.length; i++)
                parents[i] = i;
        }

        int find(int i) {
            if(parents[i] != i) {
                parents[i] = find(parents[i]);
            }
            return parents[i];
        }

        void union(int i, int j) {
            int x = find(i);
            int y = find(j);
            if(x != y) {
                if(weighted[x] >= weighted[y]) {
                    parents[y] = x;
                    weighted[x]++;
                } else {
                    parents[x] = y;
                    weighted[y]++;
                }
            }
        }
    }
    public int unionFindSolution(int N, int[][] connections) {
        DisjointSet ds = new DisjointSet(N);

        Arrays.sort(connections, (a, b) -> (a[2] - b[2]));

        int minCost = 0;
        int countEdge = 0;
        for(int[] edge : connections) {
            int v1 = edge[0];
            int v2 = edge[1];

            if(ds.find(v1) == ds.find(v2))
                continue;

            ds.union(v1, v2);
            minCost += edge[2];
            countEdge++;
        }

        return countEdge == N - 1 ? minCost : -1;
    }
}
