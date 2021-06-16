package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Score of a Good Subarray",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-score-of-a-good-subarray/"
)
public class Q1793 {
    /*
        1. start l,r at k
        2. extend l and r as far as possible until nums[l - 1] < min and nums[r + 1] < min
        3. pick max(nums[l - 1], nums[r + 1]) as new minimum value and repeat
    */
    public int maximumScore(int[] nums, int k) {
        int n = nums.length;
        int l = k;
        int r = k;
        int min = nums[k];
        int maxScore = nums[k];

        while(true) {
            while(r + 1 < n && nums[r + 1] >= min) {
                r++;
            }
            while(l - 1 >= 0 && nums[l - 1] >= min) {
                l--;
            }

            maxScore = Math.max(maxScore, min * (r - l + 1));

            if(l == 0 && r == n - 1) {
                break;
            }
            if(l == 0) {
                min = nums[r + 1];
            } else if(r == n - 1) {
                min = nums[l - 1];
            } else {
                min = Math.max(nums[r + 1], nums[l - 1]);
            }
        }

        return maxScore;
    }
}
