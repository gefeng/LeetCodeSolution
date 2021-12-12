package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Detonate the Maximum Bombs",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/detonate-the-maximum-bombs/"
)
public class Q2101 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int maximumDetonation(int[][] bombs) {
        int ans = 0;
        int n = bombs.length;
        List<Integer>[] g = new List[n];

        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            for(int j = 0; j < n; j++) {
                if(i != j && in(bombs[i], bombs[j])) {
                    g[i].add(j);
                }
            }
        }
        for(int i = 0; i < n; i++) {
            int cnt = dfs(g, i, new boolean[n]);
            ans = Math.max(ans, cnt);
        }

        return ans;
    }

    private boolean in(int[] x, int[] y) {
        long x1 = x[0];
        long y1 = x[1];
        long r1 = x[2];
        long x2 = y[0];
        long y2 = y[1];
        long r2 = y[2];
        long d = (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);

        return d <= r1 * r1;
    }

    private int dfs(List<Integer>[] g,  int cur, boolean[] visited) {
        visited[cur] = true;
        int cnt = 1;
        for(int nei : g[cur]) {
            if(!visited[nei]) {
                cnt += dfs(g, nei, visited);
            }
        }
        return cnt;
    }
}
