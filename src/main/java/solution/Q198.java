package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "House Robber",
        difficulty = QDifficulty.EASY,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/house-robber/"
)
public class Q198 {
    public int rob(int[] nums) {
        int currMax = 0;
        int prevMax = 0;
        for(int n : nums) {
            int temp = currMax;
            currMax = Math.max(prevMax + n, currMax);
            prevMax = temp;
        }

        return currMax;
    }
}
