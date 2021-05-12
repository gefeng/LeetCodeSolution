package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Length of Pair Chain",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-length-of-pair-chain/"
)
public class Q646 {
    public int findLongestChain(int[][] pairs) {
        return dpSolution(pairs);
    }

    /*
        state:
            dp[i] means the longest chain ends at pairs[i]
        transition:
            dp[i] = max(dp[j]) + 1 for any j where pairs[i] and pairs[j] are not overlappling
    */
    private int dpSolution(int[][] pairs) {
        int n = pairs.length;
        int[] dp = new int[n];

        Arrays.sort(pairs, (a, b) -> Integer.compare(a[0], b[0]));

        int maxLen = 1;
        dp[0] = 1;
        for(int i = 1; i < n; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(pairs[i][0] > pairs[j][1]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    private int greedySolution(int[][] pairs) {
        Arrays.sort(pairs, (a, b) -> Integer.compare(a[1], b[1]));

        int n = pairs.length;
        int maxLen = 1;
        int end = pairs[0][1];
        for(int i = 1; i < n; i++) {
            if(pairs[i][0] > end) {
                maxLen++;
                end = pairs[i][1];
            }
        }

        return maxLen;
    }
}
