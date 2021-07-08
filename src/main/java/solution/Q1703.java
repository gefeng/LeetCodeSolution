package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Adjacent Swaps for K Consecutive Ones",
        difficulty = QDifficulty.HARD,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/minimum-adjacent-swaps-for-k-consecutive-ones/"
)
public class Q1703 {
    /*
        [0 0 1 0 1 1 0 1 0 0 1 0 1]
        [2 4 5 7 10 12]

        if k = 5 (odd)
        [2 4 5 7 10] optimal rearrange will be moving all the ones to median -> [3 4 5 6 7]
        we can first [2 4 5 7 10] -> [5, 5, 5, 5, 5]
        whose cost is x = (5 - 2) + (5 - 4) + (7 - 5) + (10 - 5) = (7 + 10) - (2 + 4)
        then [5 5 5 5 5] -> [3 4 5 6 7]
        whose cost is y = (1 + k / 2) * (k / 2) / 2 * 2 = (k / 2) * (k / 2 + 1)
        total cost is x - y

        if k = 4 (even)
        [2 4 5 7] -> [3 4 5 6]
        first [2 4 5 7] -> [5 5 5 5]
        whose cost is x = (5 - 2) + (5 - 4) + (5 - 5) + (7 - 5) = (5 + 7) - (2 + 4)
        then [5 5 5 5] -> [3 4 5 6]
        whose cost is y = (1 + k / 2) * (k / 2) / 2 + (1 + k / 2 - 1) * (k / 2 - 1) / 2
        total cost is x - y
    */
    public int minMoves(int[] nums, int k) {
        int n = nums.length;
        int ans = Integer.MAX_VALUE;
        int cntOne = 0;
        for(int num : nums) {
            cntOne = num == 1 ? cntOne + 1 : cntOne;
        }

        int[] ones = new int[cntOne];
        for(int i = 0, j = 0; i < n; i++) {
            if(nums[i] == 1) {
                ones[j++] = i;
            }
        }

        int[] prefixSum = new int[cntOne + 1];
        for(int i = 1; i < cntOne + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + ones[i - 1];
        }

        for(int l = 0, r = 0; r < cntOne; r++) {
            if(r - l + 1 == k) {
                int median = k / 2;
                int costOfMedian = 0;
                int costOfSpread = 0;
                if(k % 2 == 0) {  // even
                    costOfMedian = (prefixSum[r + 1] - prefixSum[l + median]) - (prefixSum[l + median] - prefixSum[l]);
                    costOfSpread = (median * (median + 1)) / 2 + (median * (median - 1)) / 2;
                } else {          // odd
                    costOfMedian = (prefixSum[r + 1] - prefixSum[l + median + 1]) - (prefixSum[l + median] - prefixSum[l]);
                    costOfSpread = median * (median + 1);
                }
                ans = Math.min(ans, costOfMedian - costOfSpread);
                l++;
            }
        }

        return ans;
    }
}
