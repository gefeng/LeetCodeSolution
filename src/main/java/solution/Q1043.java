package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Partition Array for Maximum Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/partition-array-for-maximum-sum/"
)
public class Q1043 {
    /**
     * Time:  O(N * K)
     * Space: O(N)
     * */
    public int maxSumAfterPartitioning(int[] arr, int k) {
        //return dfs(arr, k, 0, new Integer[arr.length]);
        return bottomUpSol(arr, k);
    }

    private int dfs(int[] arr, int k, int start, Integer[] memo) {
        if(start == arr.length) {
            return 0;
        }

        if(memo[start] != null) {
            return memo[start];
        }

        int ret = 0;
        int max = 0;

        for(int i = start; i < Math.min(start + k, arr.length); i++) {
            max = Math.max(max, arr[i]);

            ret = Math.max(ret, dfs(arr, k, i + 1, memo) + max * (i - start + 1));
        }

        return memo[start] = ret;
    }

    /*
        state:
            dp[i] denotes largest sum of first i elements.
        transition:
            dp[i] = max(dp[i - j] + sum(arr[i - j] .. arr[i - 1])) j from [1, k]
    */
    private int bottomUpSol(int[] arr, int k) {
        int n = arr.length;
        int[] dp = new int[n + 1];

        for(int i = 1; i <= n; i++) {
            int max = 0;
            for(int j = 1; j <= k && i - j >= 0; j++) {
                max = Math.max(max, arr[i - j]);
                dp[i] = Math.max(dp[i], dp[i - j] + max * j);
            }
        }

        return dp[n];
    }
}
