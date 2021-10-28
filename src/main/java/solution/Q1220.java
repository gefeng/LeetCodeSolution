package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Count Vowels Permutation",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-vowels-permutation/"
)
public class Q1220 {
    /*
        dp[i][j] means number of strings of length i under the rules end with vowels j
        dp[i][j] = if j = 0 then dp[i - 1][1] + dp[i - 1][2] + dp[i - 1][4];
                 = if j = 1 then dp[i - 1][0] + dp[i - 1][2]
                 = if j = 2 then dp[i - 1][1] + dp[i - 1][3]
                 = if j = 3 then dp[i - 1][2]
                 = if j = 4 then dp[i - 1][2] + dp[i - 1][3]
    */
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int countVowelPermutation(int n) {
        int[] dp = new int[5];
        Arrays.fill(dp, 1);

        for(int i = 2; i < n + 1; i++) {
            int[] next = new int[5];

            next[0] = ((dp[1] + dp[2]) % MOD + dp[4]) % MOD;
            next[1] = (dp[0] + dp[2]) % MOD;
            next[2] = (dp[1] + dp[3]) % MOD;
            next[3] = (dp[2]) % MOD;
            next[4] = (dp[2] + dp[3]) % MOD;

            dp = next;
        }


        int res = 0;
        for(int i = 0; i < 5; i++) {
            res = (res + dp[i]) % MOD;
        }
        return res;
    }
}
