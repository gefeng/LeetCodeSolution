package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check If All 1's Are at Least Length K Places Away",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/check-if-all-1s-are-at-least-length-k-places-away/"
)
public class Q1437 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean kLengthApart(int[] nums, int k) {
        int n = nums.length;

        int prev = -1;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 1) {
                if(prev != -1 && i - prev - 1 < k) {
                    return false;
                }

                prev = i;
            }
        }

        return true;
    }
}
