package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Removing Minimum and Maximum From Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/removing-minimum-and-maximum-from-array/"
)
public class Q2091 {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if(n == 1) {
            return 1;
        }

        int minIdx = 0;
        int maxIdx = 0;

        for(int i = 1; i < nums.length; i++) {
            if (nums[i] > nums[maxIdx]) {
                maxIdx = i;
            }
            if (nums[i] < nums[minIdx]) {
                minIdx = i;
            }
        }

        if(minIdx > maxIdx) {
            int d = minIdx; minIdx = maxIdx; maxIdx = d;
        }

        return Math.min(n - (maxIdx - minIdx - 1), Math.min(maxIdx + 1, n - minIdx));
    }
}
