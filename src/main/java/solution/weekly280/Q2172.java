package solution.weekly280;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum AND Sum of Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-280/problems/maximum-and-sum-of-array/"
)
public class Q2172 {
    /**
     * 3-base bit mask.
     *
     * Time:  O(N * K * 3 ^ K)
     * Space: O(N * 3 ^ K)
     * */
    int[] h;
    public int maximumANDSum(int[] nums, int numSlots) {
        int n = nums.length;

        // 3-base terms
        h = new int[numSlots + 1];
        int d = 1;
        for(int i = 0; i <= numSlots; i++) {
            h[i] = d;
            d *= 3;
        }

        return dfs(nums, numSlots, 0, 0, new Integer[n][h[numSlots]]);
    }

    private int dfs(int[] nums, int numSlots, int cur, int mask, Integer[][] memo) {
        int n = nums.length;
        if(cur == n) return 0;
        if(memo[cur][mask] != null) return memo[cur][mask];

        int max = 0;
        int val = nums[cur];

        for(int i = 0; i < numSlots; i++) {
            int bit = (mask % h[i + 1]) / h[i];
            if(bit == 2) continue;

            int sum = val & (i + 1);
            max = Math.max(max, dfs(nums, numSlots, cur + 1, mask + h[i], memo) + sum);
        }

        return memo[cur][mask] = max;
    }
}
