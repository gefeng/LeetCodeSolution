package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Sum of 3 Non-Overlapping Subarrays",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/maximum-sum-of-3-non-overlapping-subarrays/"
)
public class Q689 {
    /*
        [1, 2, 1, 2, 6, 7, 5, 1]   k = 2
         0  1  2  3  4  5  6  7

        prefixSum [0,1,3,4,6,12,19,24,25]

        lmaxIndex [-1,0, 0, 0, 3, 4, 4, 4]
        rmaxIndex [4, 4, 4, 4, 4, 5, 6,-1]
    */
    public int[] maxSumOfThreeSubarrays(int[] nums, int k) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        prefixSum[0] = 0;
        for(int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        int[] lMaxIndex = new int[n];
        int[] rMaxIndex = new int[n];
        int max = Integer.MIN_VALUE;

        for(int i = k - 1; i < n; i++) {
            int sum = prefixSum[i + 1] - prefixSum[i + 1 - k];
            if(sum > max) {
                max = sum;
                lMaxIndex[i] = i - k + 1;
            } else {
                lMaxIndex[i] = lMaxIndex[i - 1];
            }
        }

        max = Integer.MIN_VALUE;
        for(int i = n - k; i >= 0; i--) {
            int sum = prefixSum[i + k] - prefixSum[i];
            if(sum >= max) {
                max = sum;
                rMaxIndex[i] = i;
            } else {
                rMaxIndex[i] = rMaxIndex[i + 1];
            }
        }

        int[] ans = new int[3];
        max = Integer.MIN_VALUE;
        for(int i = k; i <= n - 2 * k; i++) {
            int lSum = prefixSum[lMaxIndex[i - 1] + k] - prefixSum[lMaxIndex[i - 1]];
            int mSum = prefixSum[i + k] - prefixSum[i];
            int rSum = prefixSum[rMaxIndex[i + k] + k] - prefixSum[rMaxIndex[i + k]];
            int sum = lSum + mSum + rSum;
            if(sum > max) {
                max = sum;
                ans[0] = lMaxIndex[i - 1];
                ans[1] = i;
                ans[2] = rMaxIndex[i + k];
            }
        }

        return ans;
    }
}
