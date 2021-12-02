package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Music Playlists",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-music-playlists/"
)
public class Q920 {
    /**
     * state:
     *  dp[i][j] denotes number of playlist with i songs played and j unique songs.
     * transition:
     *  dp[i][j] = dp[i - 1][j - 1] * (n - j + 1)
     *           + dp[i - 1][j] * max((j - k), 0)
     *
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private static final int MOD = (int)1e9 + 7;
    private int n;
    private int g;
    private int k;
    public int numMusicPlaylists(int n, int goal, int k) {
        this.n = n;
        this.g = goal;
        this.k = k;

        return topDownSol();
    }

    private int topDownSol() {
        return (int)dfs(0, 0, new Long[g + 1][n + 1]);
    }

    private long dfs(int tot, int unique, Long[][] memo) {
        if(tot == g) {
            if(unique == n) return 1;
            return 0;
        }

        if(memo[tot][unique] != null) {
            return memo[tot][unique];
        }

        long ret = 0;
        if(unique < n) {
            ret = (ret + dfs(tot + 1, unique + 1, memo) * (n - unique)) % MOD;
        }

        if(unique > k) {
            ret = (ret + dfs(tot + 1, unique, memo) * (unique - k)) % MOD;
        }

        return memo[tot][unique] = ret;
    }

    private int bottomUpSol() {
        long[][] dp = new long[g + 1][n + 1];
        dp[0][0] = 1L;

        for(int i = 1; i <= g; i++) {
            for(int j = 1; j <= n; j++) {
                dp[i][j] = ((dp[i - 1][j - 1] * (n - j + 1) % MOD) + (dp[i - 1][j] * Math.max((j - k), 0) % MOD)) % MOD;
            }
        }

        return (int)dp[g][n];
    }
}
