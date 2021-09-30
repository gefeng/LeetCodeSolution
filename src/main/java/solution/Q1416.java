package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Restore the Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/restore-the-array/"
)
public class Q1416 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int numberOfArrays(String s, int k) {
        return dfs(s, k, 0, 0, new Integer[s.length()][11]);
    }

    private int dfs(String s, int k, int curr, int preLen, Integer[][] memo) {
        int n = s.length();
        if(curr == n) {
            return 1;
        }

        if(memo[curr][preLen] != null) {
            return memo[curr][preLen];
        }

        char d = s.charAt(curr);
        int cnt = 0;

        if(d != '0' && (d - '0') <= k) {
            cnt = (cnt + dfs(s, k, curr + 1, 1, memo)) % MOD;
        }

        if(curr > 0 && isValid(s, k, curr, preLen)) {
            cnt = (cnt + dfs(s, k, curr + 1, preLen + 1, memo)) % MOD;
        }

        return memo[curr][preLen] = cnt;
    }

    private boolean isValid(String s, int k, int curr, int preLen) {
        if(preLen >= 10) {
            return false;
        }

        long num = 0;
        for(int i = curr - preLen; i <= curr; i++) {
            num = num * 10 + s.charAt(i) - '0';
        }

        if(num <= k) {
            return true;
        }

        return false;
    }
}
