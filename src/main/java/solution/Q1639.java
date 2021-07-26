package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Ways to Form a Target String Given a Dictionary",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-ways-to-form-a-target-string-given-a-dictionary/"
)
public class Q1639 {
    /**
     * M: len(target)
     * N: len(word[i])
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numWays(String[] words, String target) {
        int m = words.length;
        int n = words[0].length();
        int[][] cntCol = new int[n][26];

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < m; j++) {
                char c = words[j].charAt(i);
                cntCol[i][c - 'a']++;
            }
        }

        return dfs(cntCol, target, 0, 0, new Integer[target.length()][n]);
    }

    private int dfs(int[][] cntCol, String t, int k, int ti, Integer[][] memo) {
        if(ti == t.length()) {
            return 1;
        }
        if(k == cntCol.length || (t.length() - ti > cntCol.length - k)) {
            return 0;
        }

        if(memo[ti][k] != null) {
            return memo[ti][k];
        }


        long res = 0;

        res = (res + dfs(cntCol, t, k + 1, ti, memo)) % MOD;

        char c = t.charAt(ti);
        if(cntCol[k][c - 'a'] > 0) {
            res = (res + (long)cntCol[k][c - 'a'] * dfs(cntCol, t, k + 1, ti + 1, memo)) % MOD;
        }

        return memo[ti][k] = (int)res;
    }
}
