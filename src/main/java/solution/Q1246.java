package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Palindrome Removal",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/palindrome-removal/"
)
public class Q1246 {
    /**
     * state:
     *  dp[i][j] denotes min moves to remove arr[i, j]
     * transition:
     *  dp[i][j] = min(remove arr[i, j], remove arr[i, k] + remove arr[k + 1, j]) for k between [i, j]
     *
     * Time:  O(N ^ 3)
     * Space: O(N ^ 2)
     * */
    public int minimumMoves(int[] arr) {
        int n = arr.length;
        int[][] dp = new int[n][n];

        for(int i = n - 1; i >= 0; i--) {
            for(int j = i; j < n; j++) {
                if(i == j) {
                    dp[i][j] = 1;
                } else if(j - i == 1) {
                    dp[i][j] = arr[i] == arr[j] ? 1 : 2;
                } else {
                    if(arr[i] == arr[j]) {
                        dp[i][j] = dp[i + 1][j - 1];
                    } else {
                        dp[i][j] = Math.min(dp[i + 1][j], dp[i][j - 1]) + 1;
                    }
                }

                for(int k = i; k < j; k++) {
                    dp[i][j] = Math.min(dp[i][j], dp[i][k] + dp[k + 1][j]);
                }
            }
        }

        return dp[0][n - 1];
    }
}
