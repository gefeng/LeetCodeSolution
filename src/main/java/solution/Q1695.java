package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Erasure Value",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/maximum-erasure-value/"
)
public class Q1695 {
    /*
    * maintain a window contains only unique elements
    * */
    public int maximumUniqueSubarray(int[] nums) {
        int n = nums.length;
        int l = 0;
        int r = 0;
        int maxSum = 0;
        int currSum = 0;
        boolean seen[] = new boolean[10001];
        while(r < n) {
            int x = nums[r];
            while(seen[x]) {
                seen[nums[l]] = false;;
                currSum -= nums[l];
                l++;
            }

            seen[x] = true;

            currSum += x;
            maxSum = Math.max(maxSum, currSum);

            r++;
        }

        return maxSum;
    }
}
