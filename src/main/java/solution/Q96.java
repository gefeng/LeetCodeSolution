package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Unique Binary Search Trees",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TREE,
        url = "https://leetcode.com/problems/unique-binary-search-trees/"
)
public class Q96 {
    public int numTrees(int n) {
        int[] dp = new int[n + 1];
        dp[0] = 1;
        dp[1] = 1;
        for(int i = 2; i <= n; i++) {
            for(int j = 1; j <= i; j++)
                dp[i] += (dp[j - 1] * dp[i - j]);

        }
        return dp[n];
    }
}
