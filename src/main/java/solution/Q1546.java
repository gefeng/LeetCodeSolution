package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Maximum Number of Non-Overlapping Subarrays With Sum Equals Target",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/"
)
public class Q1546 {
    public int maxNonOverlapping(int[] nums, int target) {
        HashMap<Integer, Integer> prefixSum = new HashMap<>();
        prefixSum.put(0, -1);
        int sum = 0;
        int count = 0;
        int prevRightMost = -1;
        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            if(prefixSum.containsKey(sum - target)) {
                int left = prefixSum.get(sum - target) + 1;
                if(left > prevRightMost) {
                    count++;
                    prevRightMost = i;
                }
            }

            prefixSum.put(sum, i);
        }
        return count;
    }
}
