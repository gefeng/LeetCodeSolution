package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Maximum Number of Ways to Partition an Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/maximum-number-of-ways-to-partition-an-array/"
)
public class Q2025 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int wasdaysToPartition(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        Map<Integer, Integer> rMap = new HashMap<>();
        Map<Integer, Integer> lMap = new HashMap<>();

        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];
        }

        int sumL = 0;
        int sumR = 0;
        for(int i = 1; i < n; i++) {
            sumL += nums[i - 1];
            sumR = sum - sumL;
            rMap.put(sumL - sumR, rMap.getOrDefault(sumL - sumR, 0) + 1);
        }

        ans = rMap.getOrDefault(0, 0);

        sumL = 0;
        sumR = 0;
        for(int i = 0; i < n; i++) {
            int d = k - nums[i];
            ans += lMap.getOrDefault(d, 0);
            ans += rMap.getOrDefault(-d, 0);

            sumL += nums[i];
            sumR = sum - sumL;
            lMap.put(sumL - sumR, lMap.getOrDefault(sumL - sumR, 0) + 1);
            rMap.put(sumL - sumR, rMap.getOrDefault(sumL - sumR, 0) - 1);

            sumL += nums[i];
        }

        return ans;
    }
}
