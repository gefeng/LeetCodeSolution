package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Vacation Days",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-vacation-days/"
)
public class Q568 {
    /**
     * Time:  O(N ^ 2 * M)
     * Space: O(N * M)
     * */
    public int maxVacationDays(int[][] flights, int[][] days) {
        int n = days.length;
        int m = days[0].length;
        return dfs(flights, days, 0, 0, new Integer[n][m]);
    }

    private int dfs(int[][] flights, int[][] days, int c, int w, Integer[][] memo) {
        int n = days.length;
        int m = days[0].length;

        if(w == m) return 0;
        if(memo[c][w] != null) return memo[c][w];

        int max = 0;
        for(int i = 0; i < n; i++) {
            if(i != c && flights[c][i] == 0) continue;
            max = Math.max(max, dfs(flights, days, i, w + 1, memo) + days[i][w]);
        }

        return memo[c][w] = max;
    }
}
