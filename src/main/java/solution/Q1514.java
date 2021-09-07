package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Path with Maximum Probability",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/path-with-maximum-probability/"
)
public class Q1514 {
    /**
     * Time:  O(E * logV)
     * Space: O(E + V)
     * */
    public double maxProbability(int n, int[][] edges, double[] succProb, int start, int end) {
        List<double[]>[] adj = new List[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < edges.length; i++) {
            double u = edges[i][0];
            double v = edges[i][1];
            double p = succProb[i];
            adj[(int)u].add(new double[] {v, p});
            adj[(int)v].add(new double[] {u, p});
        }

        double[] best = new double[n];
        Queue<double[]> maxHeap = new PriorityQueue<>(Comparator.comparing(a -> a[1], Comparator.reverseOrder()));

        Arrays.fill(best, -1.0);
        maxHeap.offer(new double[] {start, 1.0});
        best[start] = 1.0;

        while(!maxHeap.isEmpty()) {
            double[] cur = maxHeap.poll();
            int node = (int)cur[0];
            double p = cur[1];

            if(node == end) {
                return p;
            }

            for(double[] nei : adj[node]) {
                double np = nei[1] * p;
                if(best[(int)nei[0]] < np) {
                    best[(int)nei[0]] = np;
                    maxHeap.offer(new double[] {nei[0], np});
                }
            }
        }

        return 0;
    }
}
