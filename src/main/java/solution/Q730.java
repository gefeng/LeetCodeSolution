package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Different Palindromic Subsequences",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-different-palindromic-subsequences/"
)
public class Q730 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int countPalindromicSubsequences(String s) {
        int n = s.length();
        return dfs(s, 0, n - 1, new Integer[n][n]) - 1;
    }

    private int dfs(String s, int st, int ed, Integer[][] memo) {
        int n = s.length();

        if(memo[st][ed] != null) return memo[st][ed];

        int cnt = 1; // empty sequence
        for(int i = 0; i < 4; i++) {
            int j = st, k = ed;
            for(; j <= ed; j++) {
                if(s.charAt(j) - 'a' == i) break;
            }
            for(; k >= st; k--) {
                if(s.charAt(k) - 'a' == i) break;
            }
            if(j > k) continue;

            cnt += 1; // char i exist

            if(j < k) {
                cnt = (cnt + dfs(s, j + 1, k - 1, memo)) % MOD;
            }
        }

        return memo[st][ed] = cnt;
    }
}
