package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Largest Divisible Subset",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/largest-divisible-subset/"
)
public class Q368 {
    /*
        sort the array in ascending order
        state: dp[i] is the largest divisible subset end with nums[i]
        transition: dp[i] = maxSubset(dp[j]) + nums[i] for every j where nums[i] % nums[j] == 0
    */
    public List<Integer> largestDivisibleSubset(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;
        List<Integer>[] dp = new List[n];
        List<Integer> maxSubset = new ArrayList<>();

        for(int i = 0; i < n; i++) {
            dp[i] = new ArrayList<>();
            List<Integer> subset = new ArrayList<>();
            for(int j = 0; j < i; j++) {
                if(nums[i] % nums[j] == 0 && dp[j].size() > subset.size()) {
                    subset = dp[j];
                }
            }
            dp[i].addAll(subset);
            dp[i].add(nums[i]);

            if(dp[i].size() > maxSubset.size()) {
                maxSubset = dp[i];
            }
        }

        return maxSubset;
    }
}
