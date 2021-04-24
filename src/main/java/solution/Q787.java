package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Cheapest Flights Within K Stops",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/cheapest-flights-within-k-stops/"
)
public class Q787 {
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int K) {
        return dijkstraSolution(n, flights, src, dst, K);
    }

    private int dijkstraSolution(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] f : flights) {
            List<int[]> successors = graph.computeIfAbsent(f[0], k -> new ArrayList<>());
            successors.add(new int[] {f[1], f[2]});
        }

        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> (Integer.compare(a[1], b[1])));
        minHeap.offer(new int[] {src, 0, 0});
        while(!minHeap.isEmpty()) {
            int[] node = minHeap.poll();

            if(node[0] == dst) {
                return node[1];
            }

            if(node[2] == K + 1) {
                continue;
            }

            List<int[]> successors = graph.get(node[0]);
            if(successors == null)
                continue;

            for(int[] s : successors) {
                minHeap.offer(new int[] {s[0], node[1] + s[1], node[2] + 1});
            }
        }

        return -1;
    }

    private int dpSolution(int n, int[][] flights, int src, int dst, int K) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for(int[] f : flights) {
            List<int[]> successors = graph.computeIfAbsent(f[0], k -> new ArrayList<>());
            successors.add(new int[] {f[1], f[2]});
        }

        return recursiveMemo(graph, src, dst, K, new Integer[n][K + 1]);
    }

    private int recursiveMemo(Map<Integer, List<int[]>> graph,
                              int src, int dst, int K, Integer[][] memo) {
        if(src == dst) {
            return 0;
        }
        if(K < 0) {
            return -1;
        }

        if(memo[src][K] != null)
            return memo[src][K];

        int minCost = -1;
        List<int[]> successors = graph.get(src);
        if(successors != null) {
            for(int[] ss : successors) {
                int cost = recursiveMemo(graph, ss[0], dst, K - 1, memo);
                if(cost != -1) {
                    minCost = minCost == -1 ? cost + ss[1] : Math.min(minCost, cost + ss[1]);
                }
            }
        }

        return memo[src][K] = minCost;
    }
}
