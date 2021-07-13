package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Decode Ways II",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/decode-ways-ii/"
)
public class Q639 {
    private static final long MOD = (long)1e9 + 7;
    public int numDecodings(String s) {
        return (int)dfs(s, 0, new Long[s.length()]);
    }

    private long dfs(String s, int start, Long[] memo) {
        int n = s.length();
        if(start == n) {
            return 1;
        }

        if(memo[start] != null) {
            return memo[start];
        }

        long pickOne = 0;
        long pickTwo = 0;

        char curr = s.charAt(start);
        if(curr == '0') {
            return 0;
        }

        if(start == n - 1) {
            return curr == '*' ? 9 : 1;
        }

        char next = s.charAt(start + 1);
        if(curr == '1') {
            pickOne = dfs(s, start + 1, memo);
            pickTwo = dfs(s, start + 2, memo);
            pickTwo = next == '*' ? (pickTwo * 9) % MOD : pickTwo;
        } else if(curr == '2') {
            pickOne = dfs(s, start + 1, memo);
            if(next == '*') {
                pickTwo = (dfs(s, start + 2, memo) * 6) % MOD;
            } else if(next >= '0' && next <= '6') {
                pickTwo = dfs(s, start + 2, memo);
            }
        } else if(curr == '*') {
            pickOne = (dfs(s, start + 1, memo) * 9) % MOD;
            if(next == '*') {
                pickTwo = (dfs(s, start + 2, memo) * (9 + 6)) % MOD;
            } else if(next >= '0' && next <= '6') {
                pickTwo = (dfs(s, start + 2, memo) * 2) % MOD;
            } else {
                pickTwo = dfs(s, start + 2, memo) % MOD;
            }
        } else {
            pickOne = dfs(s, start + 1, memo);
        }

        return memo[start] = (pickOne + pickTwo) % MOD;
    }
}
