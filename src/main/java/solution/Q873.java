package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Length of Longest Fibonacci Subsequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/length-of-longest-fibonacci-subsequence/"
)
public class Q873 {
    /**
     * state:
     *  dp[i][j] longest fib-seq with last two elements arr[i] and arr[j].
     * transition:
     *  dp[j][k] = dp[i][j] + 1 if arr[k] = arr[i] + arr[j] exists.
     *
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int lenLongestFibSubseq(int[] arr) {
        int ans = 0;
        int n = arr.length;
        int[][] dp = new int[n][n];
        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            map.put(arr[i], i);
        }

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n - 1; j++) {
                if(map.containsKey(arr[i] + arr[j])) {
                    int k = map.get(arr[i] + arr[j]);
                    dp[j][k] = dp[i][j] + 1;
                    ans = Math.max(ans, dp[j][k]);
                }
            }
        }

        return ans == 0 ? 0 : ans + 2;
    }
}
