package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Minimum Height Trees",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/minimum-height-trees/"
)
public class Q310 {
    /**
     * Not a easy problem, the conclusion for only 2 or 1 centroids exist is the key.
     * There are no more than 2 centroids.
     * Remove nodes from leaves until only 2 or 1 nodes left.
     * Algorithm is similar to topological sorting.
     *
     * Time:  O(V)
     * Space: O(V)
     * */
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if(n == 1) {
            return Arrays.asList(0);
        }
        if(n == 2) {
            return Arrays.asList(0, 1);
        }
        List<Integer> res = new ArrayList<>();
        List<Integer>[] adj = new List[n];
        int[] degree = new int[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            adj[u].add(v);
            adj[v].add(u);
            degree[u]++;
            degree[v]++;
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(degree[i] == 1) {
                queue.offer(i);
            }
        }

        int cnt = n;
        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                int cur = queue.poll();

                cnt--;

                for(int nei : adj[cur]) {
                    degree[nei]--;
                    if(degree[nei] == 1) {
                        queue.offer(nei);
                    }
                }
            }

            if(cnt < 3) {
                break;
            }
        }

        while(!queue.isEmpty()) {
            res.add(queue.poll());
        }

        return res;
    }
}
