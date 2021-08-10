package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count All Possible Routes",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-all-possible-routes/"
)
public class Q1575 {
    /**
     * Time:  O(N ^ 2 * F)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int countRoutes(int[] locations, int start, int finish, int fuel) {
        int n = locations.length;
        return dfs(locations, start, finish, fuel, new Integer[n][fuel + 1]);
    }

    private int dfs(int[] loc, int cur, int end, int fuel, Integer[][] memo) {
        if(memo[cur][fuel] != null) {
            return memo[cur][fuel];
        }

        int res = cur == end ? 1 : 0;
        for(int i = 0; i < loc.length; i++) {
            int cost = Math.abs(loc[cur] - loc[i]);
            if(i == cur || cost > fuel) {
                continue;
            }

            res = (res + dfs(loc, i, end, fuel - cost, memo)) % MOD;
        }

        return memo[cur][fuel] = res;
    }
}
