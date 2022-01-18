package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Strange Printer",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/strange-printer/"
)
public class Q664 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N ^ 3)
     * */
    public int strangePrinter(String s) {
        int n = s.length();

        return dfs(s, 0, n - 1, 0, new Integer[n][n][n]);
    }

    private int dfs(String s, int l, int r, int k, Integer[][][] memo) {
        if(l > r) return 0;
        if(memo[l][r][k] != null) return memo[l][r][k];

        int min = dfs(s, l + 1, r, k, memo) + 1;
        for(int i = l + 1; i <= r; i++) {
            if(s.charAt(i) == s.charAt(l)) {
                min = Math.min(min, dfs(s, l + 1, i - 1, 0, memo) + dfs(s, i, r, k + 1, memo));
            }
        }

        return memo[l][r][k] = min;
    }
}
