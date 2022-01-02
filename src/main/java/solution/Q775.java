package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Global and Local Inversions",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/global-and-local-inversions/"
)
public class Q775 {
    public boolean isIdealPermutation(int[] nums) {
        int n = nums.length;

        // local is global => find global is non-local
        int max = 0;
        for(int i = 0; i < n - 2; i++) {
            max = Math.max(max, nums[i]);

            if(nums[i + 2] < max) return false;
        }

        return true;
    }
}
