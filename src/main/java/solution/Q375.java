package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Guess Number Higher or Lower II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GAME_THEORY,
        url = "https://leetcode.com/problems/guess-number-higher-or-lower-ii/"
)
public class Q375 {
    /**
     * Typical min/max game theory problem.
     * The goal is to minimize the maximum lost which is a optimal strategy.
     *
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    public int getMoneyAmount(int n) {
        return dfs(1, n, new Integer[n + 1][n + 1]);
    }

    private int dfs(int l, int r, Integer[][] memo) {
        if(l >= r) {
            return 0;
        }

        if(memo[l][r] != null) {
            return memo[l][r];
        }

        int res = Integer.MAX_VALUE;
        for(int i = l; i <= r; i++) {
            res = Math.min(res, Math.max(dfs(l, i - 1, memo), dfs(i + 1, r, memo)) + i);
        }

        return memo[l][r] = res;
    }
}
