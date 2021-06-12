package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Dungeon Game",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/dungeon-game/"
)
public class Q174 {
    public int calculateMinimumHP(int[][] dungeon) {
        return topDownSolution(dungeon);
    }

    private int topDownSolution(int[][] dungeon) {
        return dfs(dungeon, 0, 0, new Integer[dungeon.length][dungeon[0].length]);
    }

    private int dfs(int[][] dungeon, int r, int c, Integer[][] memo) {
        int m = dungeon.length;
        int n = dungeon[0].length;

        if(r == m || c == n) {
            return Integer.MAX_VALUE;
        }

        if(r ==  m - 1 && c == n - 1) {
            return dungeon[r][c] >= 0 ? 1 : 1 - dungeon[r][c];
        }

        if(memo[r][c] != null) {
            return memo[r][c];
        }

        int fromRight = dfs(dungeon, r + 1, c, memo);
        int fromBottom = dfs(dungeon, r, c + 1, memo);
        int minHp = Math.min(fromRight, fromBottom) - dungeon[r][c];

        return memo[r][c] = minHp <= 0 ? 1 : minHp;
    }
}
