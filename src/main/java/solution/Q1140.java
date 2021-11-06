package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Stone Game II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/stone-game-ii/"
)
public class Q1140 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] preSum = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + piles[i - 1];
        }

        return dfs(piles, preSum, 0, 1, new Integer[n][2 * n + 1]);
    }

    private int dfs(int[] piles, int[] preSum, int start, int M, Integer[][] memo) {
        int n = piles.length;

        if(start == n) {
            return 0;
        }

        if(memo[start][M] != null) {
            return memo[start][M];
        }

        int max = 0;
        int tot = preSum[n] - preSum[start];
        for(int i = start; i < Math.min(n, start + 2 * M); i++) {
            max = Math.max(max, tot - dfs(piles, preSum, i + 1, Math.max(M, i - start + 1), memo));
        }

        return memo[start][M] = max;
    }
}
