package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Edit Distance",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/edit-distance/"
)
public class Q72 {
    public int minDistance(String word1, String word2) {
        return dfsMinDistance(word1, word2, 0, 0, new Integer[word1.length()][word2.length()]);
    }

    private int dfsMinDistance(String word1, String word2, int i, int j, Integer[][] memo) {
        if(i == word1.length())
            return word2.length() - j;
        if(j == word2.length())
            return word1.length() - i;

        if(memo[i][j] != null)
            return memo[i][j];

        int min = Integer.MAX_VALUE;
        if(word1.charAt(i) == word2.charAt(j))
            min = dfsMinDistance(word1, word2, i + 1, j + 1, memo);
        else {
            int insert = dfsMinDistance(word1, word2, i, j + 1, memo);
            int delete = dfsMinDistance(word1, word2, i + 1, j, memo);
            int replace = dfsMinDistance(word1, word2, i + 1, j + 1, memo);
            min = Math.min(Math.min(insert, delete), replace) + 1;
        }

        memo[i][j] = min;
        return min;
    }

    /*
        state:
            dp[i][j] means ED between word1[0, i) and word2[0, j)
        transition:
            dp[i][j] = dp[i - 1][j - 1] if word1[i - 1] == word2[j - 1]
                     = min(dp[i - 1][j - 1], dp[i - 1][j], dp[i][j - 1]) + 1

        if we know dp[i - 1][j] we can perform one more step that delete word1[i - 1] to make prefix equal
        if we know dp[i][j - 1] we can perform one more step that insert word2[j - 1] at position i - 1 for make prefix equal
    */
    private int dpSolution(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] dp = new int[m + 1][n + 1];

        for(int i = 0; i < m + 1; i++) {
            dp[i][0] = i;
        }

        for(int i = 0; i < n + 1; i++) {
            dp[0][i] = i;
        }


        for(int i = 1; i < m + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                if(word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.min(dp[i - 1][j - 1], Math.min(dp[i - 1][j], dp[i][j - 1])) + 1;
                }
            }
        }

        return dp[m][n];
    }
}
