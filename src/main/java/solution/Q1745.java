package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Palindrome Partitioning IV",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/palindrome-partitioning-iv/"
)
public class Q1745 {
    /*
    * 这题很好，用dp找出所有palindrome subarray，再split。
    * 看了一些posts，如果题目要求什么分2段，3段直接for loop brute force就完了，不用再dp了
    * */
    public boolean checkPartitioning(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                if(j - i < 2) {
                    dp[i][j] = s.charAt(i) == s.charAt(j);
                } else {
                    dp[i][j] = dp[i + 1][j - 1] && s.charAt(i) == s.charAt(j);
                }
            }
        }

        for(int i = 1; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                if(dp[0][i - 1] && dp[i][j - 1] && dp[j][n - 1]) {
                    return true;
                }
            }
        }

        return false;
    }
}
