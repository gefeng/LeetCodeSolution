package solution.weekly299;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Minimum Score After Removals on a Tree",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/contest/weekly-contest-299/problems/minimum-score-after-removals-on-a-tree/"
)
public class Q2322 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     *
     * Note space can be optimized by saving time stamp when entering a node and leaving a node. This is a new trick
     * for me.
     * */
    public int minimumScore(int[] nums, int[][] edges) {
        int n = nums.length;
        int m = edges.length;
        int ans = Integer.MAX_VALUE;

        int[] dp = new int[n];
        int[] pa = new int[n];
        List<Integer>[] g = new List[n];


        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int[] e : edges) {
            g[e[0]].add(e[1]);
            g[e[1]].add(e[0]);
        }

        dfs(g, nums, 0, -1, dp, pa);

        boolean[][] anc = new boolean[n][n];
        for(int i = 0; i < n; i++) {
            int j = i;
            while(pa[j] != -1) {
                anc[i][pa[j]] = true;
                j = pa[j];
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = i + 1; j < m; j++) {
                int[] e1 = edges[i];
                int[] e2 = edges[j];

                int p1 = pa[e1[0]] == e1[1] ? e1[0] : e1[1];
                int p2 = pa[e2[0]] == e2[1] ? e2[0] : e2[1];
                int x = dp[0];
                int y = dp[p1];
                int z = dp[p2];

                if(anc[p1][p2]) {
                    x ^= z;
                    z ^= y;
                } else if(anc[p2][p1]) {
                    x ^= y;
                    y ^= z;
                } else {
                    x ^= y;
                    x ^= z;
                }
                int min = Math.min(x, Math.min(y, z));
                int max = Math.max(x, Math.max(y, z));
                ans = Math.min(ans, max - min);
            }
        }

        return ans;
    }

    private int dfs(List<Integer>[] g, int[] nums, int cur, int p, int[] dp, int[] pa) {
        int xor = nums[cur];
        for(int nei : g[cur]) {
            if(nei != p) {
                xor ^= dfs(g, nums, nei, cur, dp, pa);
            }
        }

        pa[cur] = p;
        dp[cur] = xor;

        return xor;
    }
}
