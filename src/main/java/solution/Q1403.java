package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Minimum Subsequence in Non-Increasing Order",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-subsequence-in-non-increasing-order/"
)
public class Q1403 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public List<Integer> minSubsequence(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        int n = nums.length;
        int sum = 0;

        Arrays.sort(nums);

        for(int i = 0; i < n; i++) {
            sum += nums[i];
        }

        int subsum = 0;
        for(int i = n - 1; i >= 0; i--) {
            if(subsum <= sum - subsum) {
                subsum += nums[i];
                ans.add(nums[i]);
            } else {
                break;
            }
        }

        return ans;
    }
}
