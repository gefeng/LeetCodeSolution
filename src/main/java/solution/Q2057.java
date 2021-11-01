package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Smallest Index With Equal Value",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/smallest-index-with-equal-value/"
)
public class Q2057 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int smallestEqual(int[] nums) {
        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(i % 10 == nums[i]) return i;
        }
        return -1;
    }
}
