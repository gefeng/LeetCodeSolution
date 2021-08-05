package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Stone Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/stone-game/"
)
public class Q877 {
    /**
     * Each player wants to maximize the score difference.
     * Therefore return the max score difference can be achieved by opponent.
     *
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        return dfs(piles, 0, n - 1, new Integer[n][n]) > 0;
    }

    private int dfs(int[] piles, int start, int end, Integer[][] memo) {
        if(start == end) {
            return piles[start];
        }

        if(memo[start][end] != null) {
            return memo[start][end];
        }

        int a = dfs(piles, start + 1, end, memo);
        int b = dfs(piles, start, end - 1, memo);

        return memo[start][end] = Math.max(piles[start] - a, piles[end] - b);
    }
}
