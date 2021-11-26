package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Knight Dialer",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/knight-dialer/"
)
public class Q935 {
    /**
     * Could be simplify since for each cell there are only limited valid move to the next one.
     * 
     * Time:  O(N * 10 * 8)
     * Space: O(N * 10)
     * */
    private static final int MOD = (int)1e9 + 7;
    private static final int[][] DIRECTIONS = new int[][] {{2, 1}, {2, -1}, {1, -2}, {-1, -2},
            {-2, -1}, {-2, 1}, {-1, 2}, {1, 2}};
    private static final int[][] PAD = new int[][] {{3, 1}, {0, 0}, {0, 1}, {0, 2}, {1, 0}, {1, 1}, {1, 2}, {2, 0}, {2, 1}, {2, 2}};
    private static final int[] STAR = new int[] {3, 0};
    private static final int[] HASH = new int[] {3, 2};
    public int knightDialer(int n) {
        int ans = 0;
        int[][] dp = new int[n + 1][10];

        for(int i = 0; i < 10; i++) {
            dp[1][i] = 1;
        }

        for(int i = 1; i <= n; i++) {
            for(int j = 0; j < 10; j++) {
                int[] pos = PAD[j];
                for(int[] dir : DIRECTIONS) {
                    int pr = pos[0] + dir[0];
                    int pc = pos[1] + dir[1];
                    int k = (pr == 3 && pc == 1) ? 0 : pr * 3 + pc + 1;

                    if(pr >= 0 && pc >= 0 && pr < 4 && pc < 3) {
                        if((pr != STAR[0] || pc != STAR[1]) && (pr != HASH[0] || pc != HASH[1])) {
                            dp[i][j] = (dp[i][j] + dp[i - 1][k]) % MOD;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < 10; i++) {
            ans = (ans + dp[n][i]) % MOD;
        }

        return ans;
    }
}
