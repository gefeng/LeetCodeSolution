package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Palindrome Partitioning II",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/palindrome-partitioning-ii/"
)
public class Q132 {
    /*
        state:
            dp1[i] means minimum cuts of s[0, i)
        transition:
            dp1[i] = min(dp1[j]) + 1 for j from [0, i - 1] if s[j, i) is palindrome

        state:
            dp2[i][j] means s[i, j] is or not a palindrome
        transition:
            dp2[i][j] = true if i == j
            dp2[i][j] = s[i] == s[j] if j - i == 1
            dp2[i][j] = dp2[i + 1][j - 1] && s[i] == s[j] if j - i > 1
    */
    public int minCut(String s) {
        int n = s.length();
        int[] dp1 = new int[n + 1];
        boolean[][] dp2 = new boolean[n][n];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j <= i; j++) {
                if(s.charAt(i) == s.charAt(j) && (i - j <= 1 || dp2[j + 1][i - 1])) {
                    dp2[j][i] = true;
                }
            }
        }

        dp1[0] = -1;
        for(int i = 1; i < n + 1; i++) {
            dp1[i] = i - 1;
            for(int j = 0; j < i; j++) {
                if(dp2[j][i - 1]) {
                    dp1[i] = Math.min(dp1[i], dp1[j] + 1);
                }
            }
        }

        return dp1[n];
    }
}
