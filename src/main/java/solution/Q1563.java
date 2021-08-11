package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Stone Game V",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/stone-game-v/"
)
public class Q1563 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        int[] preSum = new int[n + 1];
        for(int i = 1; i <= n; i++) {
            preSum[i] = preSum[i - 1] + stoneValue[i - 1];
        }

        return dfs(stoneValue, preSum, 0, n - 1, new Integer[n][n]);
    }

    private int dfs(int[] s, int[] ps, int start, int end, Integer[][] memo) {
        if(start == end) {
            return 0;
        }

        if(memo[start][end] != null) {
            return memo[start][end];
        }

        int score = 0;
        for(int i = start; i < end; i++) {
            int l = ps[i + 1] - ps[start];
            int r = ps[end + 1] - ps[i + 1];
            if(l == r) {
                score = Math.max(score, Math.max(dfs(s, ps, start, i, memo), dfs(s, ps, i + 1, end, memo)) + l);
            } else if(l < r) {
                score = Math.max(score, dfs(s, ps, start, i, memo) + l);
            } else {
                score = Math.max(score, dfs(s, ps, i + 1, end, memo) + r);
            }
        }

        return memo[start][end] = score;
    }
}
