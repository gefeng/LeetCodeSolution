package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Palindromic Subsequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/longest-palindromic-subsequence/"
)
public class Q516 {
    /*
    * 难！
    * */
    public int longestPalindromeSubseq(String s) {
        int len = s.length();
        return recursiveMemo(s, 0, len - 1, new Integer[len][len]);
    }

    private int recursive(String s, int l, int r) {
        if(l == r) {
            return 1;
        }
        if(l > r) {
            return 0; // aa
        }

        int max = 0;
        if(s.charAt(l) == s.charAt(r)) {
            max = recursive(s, l + 1, r - 1) + 2;
        } else {
            max = Math.max(recursive(s, l + 1, r), recursive(s, l, r -1));
        }

        return max;
    }

    private int recursiveMemo(String s, int l, int r, Integer[][] memo) {
        if(l == r) {
            return 1;
        }
        if(l > r) {
            return 0;
        }

        if(memo[l][r] != null)
            return memo[l][r];

        int max = 0;
        if(s.charAt(l) == s.charAt(r)) {
            max = recursiveMemo(s, l + 1, r - 1, memo) + 2;
        } else {
            max = Math.max(recursiveMemo(s, l + 1, r, memo), recursiveMemo(s, l, r - 1, memo));
        }

        return memo[l][r] = max;
    }

    /*
        dp[i][j] means the longest palindromic seq starts at i ends at j
        dp[i][j] = dp[i + 1][j - 1] + 2 if char_i == char_j
        dp[i][j] = Math.max(dp[i][j - 1], dp[i + 1][j]) if char_i != char_j

    */
    private int dpSolution(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        for(int i = 0; i < len; i++) {
            dp[i][i] = 1;
        }

        for(int winSize = 2; winSize <= len; winSize++) {
            for(int l = 0; l <= len - winSize; l++) {
                int r = l + winSize - 1;
                if(s.charAt(l) == s.charAt(r)) {
                    dp[l][r] = dp[l + 1][r - 1] + 2;
                } else {
                    dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
                }
            }
        }

        return dp[0][len-1];
    }
}
