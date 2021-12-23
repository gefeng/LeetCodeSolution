package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Soup Servings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/soup-servings/"
)
public class Q808 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public double soupServings(int n) {
        if(n > 10000) return 1;

        return dfs(n, n, new Double[n + 1][n + 1]);
    }

    private double dfs(int a, int b, Double[][] memo) {
        if(a == 0 && b != 0) return 1;
        if(a == 0 && b == 0) return 0.5;
        if(a != 0 && b == 0) return 0;

        if(memo[a][b] != null) {
            return memo[a][b];
        }

        double res = (dfs(Math.max(a - 100, 0), b, memo) +
                dfs(Math.max(a - 75, 0), Math.max(b - 25, 0), memo) +
                dfs(Math.max(a - 50, 0), Math.max(b - 50, 0), memo) +
                dfs(Math.max(a - 25, 0), Math.max(b - 75, 0), memo)) * 0.25;

        return memo[a][b] = res;
    }
}
