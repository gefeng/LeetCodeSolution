package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Interleaving String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/interleaving-string/"
)
public class Q97 {
    public boolean isInterleave(String s1, String s2, String s3) {
        return dfs(s1, 0, s2, 0, s3, 0, new Boolean[s1.length()][s2.length()]);
    }

    private boolean dfs(String s1, int i, String s2, int j, String s3, int k, Boolean[][] memo) {
        if(i == s1.length()) {
            while(j < s2.length()) {
                if(k == s3.length() || s2.charAt(j) != s3.charAt(k))
                    return false;
                j++;
                k++;
            }
            return k == s3.length();
        }

        if(j == s2.length()) {
            while(i < s1.length()) {
                if(k == s3.length() || s1.charAt(i) != s3.charAt(k))
                    return false;
                i++;
                k++;
            }
            return k == s3.length();
        }

        if(k == s3.length()) {
            return i == s1.length() && j == s2.length();
        }

        if(memo[i][j] != null)
            return memo[i][j];

        if(s1.charAt(i) == s3.charAt(k) && dfs(s1, i + 1, s2, j, s3, k + 1, memo))
            return memo[i][j] = true;
        if(s2.charAt(j) == s3.charAt(k) && dfs(s1, i, s2, j + 1, s3, k + 1, memo))
            return memo[i][j] = true;

        return memo[i][j] = false;
    }

    /*
        dp[i][j] = dp[i - 1][j] && s1.charAt(i) == s3.charAt(i + j + 1)
        dp[i][j] = dp[i][j - 1] && s2.charAt(j) == s3.charAt(i + j + 1);
    */
    private boolean dpSolution(String s1, String s2, String s3) {
        if(s1.length() + s2.length() != s3.length())
            return false;

        boolean[][] dp = new boolean[s1.length() + 1][s2.length() + 1];

        for(int i = 0; i <= s1.length(); i++) {
            for(int j = 0; j <= s2.length(); j++) {
                if(i == 0 && j == 0) {
                    dp[i][j] = true;
                } else if(i == 0) {
                    dp[i][j] = dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1);
                } else if(j == 0) {
                    dp[i][j] = dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1);
                } else {
                    dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == s3.charAt(i + j - 1) ||
                            (dp[i][j - 1] && s2.charAt(j - 1) == s3.charAt(i + j - 1)));
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }
}
