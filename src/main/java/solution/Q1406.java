package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Stone Game III",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/stone-game-iii/"
)
public class Q1406 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String stoneGameIII(int[] stoneValue) {
        int diff = dfs(stoneValue, 0, new Integer[stoneValue.length]);
        return diff > 0 ? "Alice" : (diff == 0 ? "Tie" : "Bob");
    }

    private int dfs(int[] stoneValue, int curr, Integer[] memo) {
        int n = stoneValue.length;
        if(curr == n) {
            return 0;
        }

        if(memo[curr] != null) {
            return memo[curr];
        }

        int max = Integer.MIN_VALUE;
        int score = 0;
        for(int i = 0; i < 3; i++) {
            if(curr + i < n) {
                score += stoneValue[curr + i];
                max = Math.max(max, score - dfs(stoneValue, curr + i + 1, memo));
            }
        }

        return memo[curr] = max;
    }
}
