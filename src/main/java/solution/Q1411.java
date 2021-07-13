package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways to Paint N * 3 Grid",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-ways-to-paint-n-3-grid/"
)
public class Q1411 {
    private static final int MOD = (int)1e9 + 7;
    public int numOfWays(int n) {
        return mathSol(n);
    }

    /*
        time complexity:
            states n * 3 * 3 * 3
            each state iterate through 3 * 3 * 3
            overall O(n * 27 * 27)
    */
    private int bottomUpDpSol(int n) {
        return dfs(n, 0, 3, 3, 3, new Integer[n][4][4][4]);
    }

    private int dfs(int n, int row, int col0, int col1, int col2, Integer[][][][] memo) {
        if(row == n) {
            return 1;
        }

        if(memo[row][col0][col1][col2] != null) {
            return memo[row][col0][col1][col2];
        }

        int ret = 0;
        for(int c0 = 0; c0 < 3; c0++) {
            for(int c1 = 0; c1 < 3; c1++) {
                for(int c2 = 0; c2 < 3; c2++) {
                    if(c0 != col0 && (c1 != col1 && c1 != c0) && (c2 != col2 && c2 != c1)) {
                        ret = (ret + dfs(n, row + 1, c0, c1, c2, memo)) % MOD;
                    }
                }
            }
        }

        return memo[row][col0][col1][col2] = ret;
    }

    /*
        two patterns,
        1. xyz
        2. xyx
        for pattern 1 there are 4 outcomes -> 2 * 121 + 2 * 123
        for pattern 2 there are 5 outcomes -> 3 * 121 + 2 * 123
    */
    private int mathSol(int n) {
        long mod = (long)1e9 + 7;
        long dp123 = 6; // p33
        long dp121 = 6; // p32

        for(int i = 1; i < n; i++) {
            long next123 = (dp123 * 2 + dp121 * 2) % MOD;
            long next121 = (dp123 * 2 + dp121 * 3) % MOD;
            dp123 = next123;
            dp121 = next121;
        }

        return (int)((dp123 + dp121) % MOD);
    }
}
