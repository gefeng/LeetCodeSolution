package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Insertion Steps to Make a String Palindrome",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-insertion-steps-to-make-a-string-palindrome/"
)
public class Q1312 {
    /**
     * Using dp to find the longest palindromic subsequence.
     * The answer is n - max
     *
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int minInsertions(String s) {
        int n = s.length();
        int[][] dp = new int[n][n];

        int max = 1;
        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                if(i == j) {
                    dp[i][j] = 1;
                } else if(i + 1 == j) {
                    dp[i][j] = s.charAt(i) == s.charAt(j) ? 2 : 1;
                } else {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = dp[i + 1][j - 1] + 2;
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
        }

        return n - dp[0][n - 1];
    }
}
