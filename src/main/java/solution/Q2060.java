package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if an Original String Exists Given Two Encoded Strings",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/check-if-an-original-string-exists-given-two-encoded-strings/"
)
public class Q2060 {
    /**
     * Time:  O(M * N * 2000)
     * Space: O(M * N * 2000)
     * */
    public boolean possiblyEquals(String s1, String s2) {
        return dfs(s1, s2, 0, 0, 0, new Boolean[s1.length() + 1][s2.length() + 1][2000]);
    }

    private boolean dfs(String s1, String s2, int i, int j, int d, Boolean[][][] memo) {
        int m = s1.length();
        int n = s2.length();

        if(i == m && j == n) {
            return d == 0;
        }

        if(memo[i][j][d + 1000] != null) {
            return memo[i][j][d + 1000];
        }

        if(i < m && j < n && Character.isLetter(s1.charAt(i)) && Character.isLetter(s2.charAt(j)) &&
                s1.charAt(i) == s2.charAt(j) && d == 0) {
            if(dfs(s1, s2, i + 1, j + 1, d, memo)) {
                return memo[i][j][d + 1000] = true;
            }
        }

        if(i < m && Character.isLetter(s1.charAt(i)) && d < 0) {
            if(dfs(s1, s2, i + 1, j, d + 1, memo)) {
                return memo[i][j][d + 1000] = true;
            }
        }

        if(j < n && Character.isLetter(s2.charAt(j)) && d > 0) {
            if(dfs(s1, s2, i, j + 1, d - 1, memo)) {
                return memo[i][j][d + 1000] = true;
            }
        }

        for(int k = i, val = 0; k < m && Character.isDigit(s1.charAt(k)); k++) {
            val = val * 10 + s1.charAt(k) - '0';
            if(dfs(s1, s2, k + 1, j, d + val, memo)) {
                return memo[i][j][d + 1000] = true;
            }
        }

        for(int k = j, val = 0; k < n && Character.isDigit(s2.charAt(k)); k++) {
            val = val * 10 + s2.charAt(k) - '0';
            if(dfs(s1, s2, i, k + 1, d - val, memo)) {
                return memo[i][j][d + 1000] = true;
            }
        }

        return memo[i][j][d + 1000] = false;

    }
}
