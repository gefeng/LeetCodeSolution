package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Loud and Rich",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/loud-and-rich/"
)
public class Q851 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int[] loudAndRich(int[][] richer, int[] quiet) {
        int n = quiet.length;
        int[] ans = new int[n];
        List<Integer>[] adj = new List[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] e : richer) {
            adj[e[1]].add(e[0]);
        }

        for(int i = 0; i < n; i++) {
            ans[i] = dfs(adj, quiet, i, new boolean[n]);
        }

        return ans;
    }

    private int dfs(List<Integer>[] adj, int[] quiet, int cur, boolean[] visited) {
        visited[cur] = true;

        int min = quiet[cur];
        int per = cur;

        for(int nei : adj[cur]) {
            if(!visited[nei]) {
                int cand =  dfs(adj, quiet, nei, visited);
                if(quiet[cand] < min) {
                    per = cand;
                    min = quiet[cand];
                }
            }
        }

        return per;
    }
}
