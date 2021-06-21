package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximize Palindrome Length From Subsequences",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximize-palindrome-length-from-subsequences/"
)
public class Q1771 {
    public int longestPalindrome(String word1, String word2) {
        return topDownDp(word1, word2);
    }

    private int maxLen = 0;
    private int bottomUpDp(String word1, String word2) {
        String s = word1 + word2;
        int n = s.length();
        dfs(s, 0, n - 1, new Integer[n][n], word1.length());
        return maxLen;
    }

    private int dfs(String s, int start, int end, Integer[][] memo, int lenA) {
        if(start == end) {
            return 1;
        }
        if(start > end) {
            return 0;
        }

        if(memo[start][end] != null) {
            return memo[start][end];
        }

        int len = 0;
        if(s.charAt(start) == s.charAt(end)) {
            len = dfs(s, start + 1, end - 1, memo, lenA) + 2;
            if(start < lenA && end > lenA - 1) {
                maxLen = Math.max(maxLen, len);
            }
        } else {
            len = Math.max(dfs(s, start + 1, end, memo, lenA),
                    dfs(s, start, end - 1, memo, lenA));
        }

        return memo[start][end] = len;
    }

    /*
        state:
            dp[i][j] means longest palindrome subsequence within s[i, j]
        transition:
            dp[i][j] =
            if s[i] == s[j] dp[i + 1][j - 1] + 2 if s[i] == s[j]
            else Math.max(dp[i + 1][j], dp[i][j - 1])
    */
    private int topDownDp(String word1, String word2) {
        int lps = 0;
        int m = word1.length();
        int n = word2.length();
        String s = word1 + word2;

        int[][] dp = new int[m + n][m + n];
        for(int i = m + n - 1; i >= 0; i--) {
            for(int j = i; j < m + n; j++) {
                if(j == i) {
                    dp[i][j] = 1;
                } else {
                    if(s.charAt(i) == s.charAt(j)) {
                        dp[i][j] = j - i > 1 ? dp[i + 1][j - 1] + 2 : 2;
                        lps = i < m && j > m - 1 ? Math.max(lps, dp[i][j]) : lps;
                    } else {
                        dp[i][j] = Math.max(dp[i + 1][j], dp[i][j - 1]);
                    }
                }
            }
        }

        return lps;
    }
}
