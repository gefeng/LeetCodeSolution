package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Maximum Size Subarray Sum Equals k",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/maximum-size-subarray-sum-equals-k/"
)
public class Q325 {
    /*
    * Save prefix sum, and check currSum - k
    * if currSum - k = prevSum -> currSum - prevSum = k
    * currSum - prevSum is the sum from map.get(prevSum) + 1 to i
    * dist = i - map.get(prevSum) - 1 + 1 = i - map.get(prevSum)
    * */
    public int maxSubArrayLen(int[] nums, int k) {
        int n = nums.length;
        int maxLen = 0;
        Map<Integer, Integer> seen = new HashMap<>();
        seen.put(0, -1);

        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += nums[i];

            Integer r = seen.get(sum - k);
            if(r != null) {
                maxLen = Math.max(maxLen, i - r);
            }

            seen.putIfAbsent(sum, i);
        }

        return maxLen;
    }
}
