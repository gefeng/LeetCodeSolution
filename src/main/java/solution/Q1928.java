package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Minimum Cost to Reach Destination in Time",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-cost-to-reach-destination-in-time/"
)
public class Q1928 {
    /*
        dijkstra with modification
        Time: O(ElogV)
        Space: O(E + V)
    */
    public int minCost(int maxTime, int[][] edges, int[] passingFees) {
        return dijkstraSol(maxTime, edges, passingFees);
    }

    private int dijkstraSol(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<int[]>[] adj = new List[n];

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int t = e[2];
            if(adj[u] == null) {
                adj[u] = new ArrayList<>();
            }
            if(adj[v] == null) {
                adj[v] = new ArrayList<>();
            }

            adj[u].add(new int[] {v, t});
            adj[v].add(new int[] {u, t});
        }

        int[] cost = new int[n];
        int[] time = new int[n];
        Arrays.fill(cost, Integer.MAX_VALUE);
        Arrays.fill(time, Integer.MAX_VALUE);

        Queue<int[]> minHeap = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) {
                return Integer.compare(a[2], b[2]);
            }
            return Integer.compare(a[1], b[1]);
        });

        minHeap.offer(new int[] {0, passingFees[0], 0});
        cost[0] = passingFees[0];
        time[0] = 0;

        while(!minHeap.isEmpty()) {
            int[] node = minHeap.poll();
            int currCity = node[0];
            int currCost = node[1];
            int currTime = node[2];

            if(currCity == n - 1) {
                return cost[n - 1];
            }

            for(int[] nei : adj[currCity]) {
                int nextCity = nei[0];
                int nextCost = passingFees[nextCity];
                int nextTime = nei[1];
                if(currTime + nextTime > maxTime) {
                    continue;
                }

                if(currCost + nextCost < cost[nextCity]) {
                    cost[nextCity] = currCost + nextCost;
                    minHeap.offer(new int[] {nextCity, cost[nextCity], currTime + nextTime});
                } else if(currTime + nextTime < time[nextCity]) {
                    time[nextCity] = currTime + nextTime;
                    minHeap.offer(new int[] {nextCity, currCost + nextCost, time[nextCity]});
                }
            }
        }

        return -1;
    }

    private int dfsDpSol(int maxTime, int[][] edges, int[] passingFees) {
        int n = passingFees.length;
        List<int[]>[] adj = new List[n];

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int t = e[2];
            if(adj[u] == null) {
                adj[u] = new ArrayList<>();
            }
            if(adj[v] == null) {
                adj[v] = new ArrayList<>();
            }

            adj[u].add(new int[] {v, t});
            adj[v].add(new int[] {u, t});
        }

        return dfs(adj, passingFees, 0, maxTime, new Integer[n][maxTime + 1]);
    }

    private int dfs(List<int[]>[] adj, int[] passingFees, int city, int timeLeft, Integer[][] memo) {
        int n = passingFees.length;
        if(city == n - 1 && timeLeft >= 0) {
            return passingFees[n - 1];
        }
        if(timeLeft <= 0) {
            return -1;
        }

        if(memo[city][timeLeft] != null) {
            return memo[city][timeLeft];
        }

        int minCost = -1;
        List<int[]> neis = adj[city];
        for(int[] nei : neis) {
            if(nei[0] == city) {
                continue;
            }

            int cost = dfs(adj, passingFees, nei[0], timeLeft - nei[1], memo);
            if(cost == -1) {
                continue;
            }

            minCost = minCost == -1 ? cost + passingFees[city] : Math.min(minCost, cost + passingFees[city]);
        }

        return memo[city][timeLeft] = minCost;
    }
}
