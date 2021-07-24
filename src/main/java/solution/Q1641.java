package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Count Sorted Vowel Strings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/count-sorted-vowel-strings/"
)
public class Q1641 {
    /**
     * state:
     *      dp[i][j] means number of strings with length i ends with vowel[j]
     * transition:
     *      dp[i][j] = sum(dp[i - 1][0...j])
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int countVowelStrings(int n) {
        return spaceCompressionDpSol(n);
    }

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int topDownDpSol(int n) {
        int[][] dp = new int[n + 1][5];
        Arrays.fill(dp[1], 1);
        for(int i = 2; i < n + 1; i++) {
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k <= j; k++) {
                    dp[i][j] += dp[i - 1][k];
                }
            }
        }

        int ans = 0;
        for(int i = 0; i < 5; i++) {
            ans += dp[n][i];
        }
        return ans;
    }

    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    private int spaceCompressionDpSol(int n) {
        int[] dp = new int[5];
        Arrays.fill(dp, 1);

        for(int i = 2; i < n + 1; i++) {
            int[] next = new int[5];
            for(int j = 0; j < 5; j++) {
                for(int k = 0; k <= j; k++) {
                    next[j] += dp[k];
                }
            }
            dp = next;
        }

        int ans = 0;
        for(int i = 0; i < 5; i++) {
            ans += dp[i];
        }
        return ans;
    }
}
