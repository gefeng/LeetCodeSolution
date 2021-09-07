package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Stone Game IV",
        difficulty = QDifficulty.HARD,
        tag = QTag.GAME_THEORY,
        url = "https://leetcode.com/problems/stone-game-iv/"
)
public class Q1510 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public boolean winnerSquareGame(int n) {
        return bottomUpDpSol(n);
    }

    private boolean topDownDpSol(int n) {
        return dfs(n, new Boolean[n + 1]);
    }

    private boolean dfs(int n, Boolean[] memo) {
        if(n == 0) {
            return false;
        }

        if(memo[n] != null) {
            return memo[n];
        }

        for(int i = 1; i * i <= n; i++) {
            if(!dfs(n - i * i, memo)) {  // if opponent loses then pick
                return memo[n] = true;
            }
        }

        return memo[n] = false;
    }

    /**
     * state:
     *  dp[i] denotes game result with i stones
     * transition:
     *  dp[i] = true if any dp[i - j * j] == false where i - j * j >= 0
     *  dp[i] = false else
     * */
    private boolean bottomUpDpSol(int n) {
        boolean[] dp = new boolean[n + 1];

        dp[1] = true;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j * j <= i; j++) {
                if(!dp[i - j * j]) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[n];
    }
}
