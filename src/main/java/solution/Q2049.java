package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Count Nodes With the Highest Score",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/count-nodes-with-the-highest-score/"
)
public class Q2049 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int countHighestScoreNodes(int[] parents) {
        int ans = 0;
        int n = parents.length;
        List<Integer>[] adj = new List[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {
            if(parents[i] == -1) {
                continue;
            }
            adj[parents[i]].add(i);
        }

        long[] score = new long[n];
        dfs(adj, n, 0, score);

        long max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, score[i]);
        }

        for(int i = 0; i < n; i++) {
            if(score[i] == max) {
                ans++;
            }
        }

        return ans;
    }

    private long dfs(List<Integer>[] adj, int n, int cur, long[] score) {
        long s = 1;
        long sum = 1;
        for(int nei : adj[cur]) {
            long size = dfs(adj, n, nei, score);
            sum += size;
            s *= size;
        }

        if(n - sum > 0) {
            s *= (n - sum);
        }

        score[cur] = s;

        return sum;
    }
}
