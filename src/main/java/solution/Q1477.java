package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Find Two Non-overlapping Sub-arrays Each with Target Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/find-two-non-overlapping-sub-arrays-each-with-target-sum/"
)
public class Q1477 {
    /**
     * The trick is to save the minimum length of subarray until ith number.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        int ans = Integer.MAX_VALUE;
        int[] dp = new int[n];

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int minLen = Integer.MAX_VALUE;
        for(int i = 0; i < n; i++) {
            sum += arr[i];

            if(map.containsKey(sum - target)) {
                int pre = map.get(sum - target);
                minLen = Math.min(minLen, i - pre);

                if(pre >= 0 && dp[pre] != Integer.MAX_VALUE) {
                    ans = Math.min(ans, dp[pre] + i - pre);
                }
            }

            dp[i] = minLen;
            map.put(sum, i);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
