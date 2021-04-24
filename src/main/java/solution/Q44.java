package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Wildcard Matching",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/wildcard-matching/"
)
public class Q44 {
    public boolean isMatch(String s, String p) {
        return dpSolution(s, p);
    }

    private boolean recursiveMemo(String s, String p) {
        return isMatch(s, p, 0, 0, new Boolean[s.length()][p.length()]);
    }

    private boolean isMatch(String s, String p, int i, int j, Boolean[][] memo) {
        if(j == p.length()) {
            return i == s.length();
        }
        if(i == s.length()) {
            while(j != p.length()) {
                if(p.charAt(j++) != '*')
                    return false;
            }
            return true;
        }

        if(memo[i][j] != null)
            return memo[i][j];

        if(p.charAt(j) == '*') {
            if(isMatch(s, p, i + 1, j + 1, memo)) // match current char
                return memo[i][j] = true;
            if(isMatch(s, p, i + 1, j, memo))  // match sequence
                return memo[i][j] = true;
            if(isMatch(s, p, i, j + 1, memo))  // match empty string
                return memo[i][j] = true;
        } else if(p.charAt(j) == '?') {
            if(isMatch(s, p, i + 1, j + 1, memo))
                return memo[i][j] = true;
        } else {
            if(p.charAt(j) == s.charAt(i) && isMatch(s, p, i + 1, j + 1, memo))
                return memo[i][j] = true;
        }

        return memo[i][j] = false;
    }


    /*
        dp[i][j]

            if char_p == '*'
                if dp[i-1][j-1] is true then dp[i][j] is true
                if dp[i-1][j] is true then dp[i][j] is true
                if dp[i][j-1] is true then dp[i][j] is true
            else if char_p == '?'
                if dp[i-1][j-1] is true then dp[i][j] is true
            else
                if dp[i-1][j-1] is true and char_p == char_s then dp[i][j] is true

           a  d  c  e  b
        *
        a
        *
        b
    */
    private boolean dpSolution(String s, String p) {
        boolean[][] dp = new boolean[s.length() + 1][p.length() + 1];

        // empty string && empty pattern
        dp[0][0] = true;

        // empty pattern
        for(int i = 1; i <= s.length(); i++) {
            dp[i][0] = false;
        }

        // empty string
        for(int i = 1; i <= p.length(); i++) {
            dp[0][i] = dp[0][i - 1] && p.charAt(i - 1) == '*';
        }

        for(int i = 1; i <= s.length(); i++) {
            for(int j = 1; j <= p.length(); j++) {
                char sc = s.charAt(i - 1);
                char pc = p.charAt(j - 1);

                if(pc == '*') {
                    dp[i][j] = (dp[i - 1][j - 1] || dp[i - 1][j] || dp[i][j - 1]);
                } else if(pc == '?') {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = (dp[i - 1][j - 1] && pc == sc);
                }
            }
        }

        return dp[s.length()][p.length()];
    }
}
