package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Tallest Billboard",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/tallest-billboard/"
)
public class Q956 {
    /**
     * Time:  O(N * max(sum))
     * Space: O(N * max(sum))
     * */
    public int tallestBillboard(int[] rods) {
        return dfs(rods, 0, 0, 0, new Integer[rods.length][5005]);
    }

    private int dfs(int[] rods, int cur, int s1, int s2, Integer[][] memo) {
        if(cur == rods.length) {
            return s1 == s2 ? s1 : -10000;
        }

        if(memo[cur][Math.abs(s1 - s2)] != null) {
            return memo[cur][Math.abs(s1 - s2)] + Math.max(s1, s2);
        }

        int max = dfs(rods, cur + 1, s1, s2, memo);

        max = Math.max(max, dfs(rods, cur + 1, s1 + rods[cur], s2, memo));
        max = Math.max(max, dfs(rods, cur + 1, s1, s2 + rods[cur], memo));

        memo[cur][Math.abs(s1 - s2)] = max - Math.max(s1, s2);

        return max;
    }
}
