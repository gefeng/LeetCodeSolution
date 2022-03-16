package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways to Build House of Cards",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-ways-to-build-house-of-cards/"
)
public class Q2189 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    public int houseOfCards(int n) {
        return dfs(n, n, new Integer[n + 1][n + 1]);
    }

    private int dfs(int max, int n, Integer[][] memo) {
        if(n == 0) return 1;
        if(max < 0) return 0;

        if(memo[max][n] != null) return memo[max][n];

        int ans = 0;
        for(int i = 2; i <= Math.min(max, n); i += 3) {
            ans += dfs(i - 3, n - i, memo);
        }
        return memo[max][n] = ans;
    }
}
