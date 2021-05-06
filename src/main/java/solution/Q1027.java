package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Arithmetic Subsequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/longest-arithmetic-subsequence/"
)
public class Q1027 {
    /*
        dp[i][j]  the longest arithmetic subsequence at index i with difference j
        range of difference: -500 - 500
        range of mapping 0 - 1001
    */
    public int longestArithSeqLength(int[] A) {
        int n = A.length;
        int maxLen = 0;
        int[][] dp = new int[n][1001];

        for(int i = 0; i < A.length; i++) {
            for(int j = 0; j < i; j++) {
                int diff = A[i] - A[j] + 500;
                dp[i][diff] = dp[j][diff] + 1;
                maxLen = Math.max(maxLen, dp[i][diff]);
            }
        }

        return maxLen + 1;
    }
}
