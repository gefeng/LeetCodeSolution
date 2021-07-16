package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Single Number III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/single-number-iii/"
)
public class Q260 {
    public int[] singleNumber(int[] nums) {
        int mask = 0;
        for(int num : nums) {
            mask ^= num;
        }

        int rightMost = mask & (-mask);

        int x = 0;
        for(int num : nums) {
            if((num & rightMost) != 0) {
                x ^= num;
            }
        }

        return new int[] {x, x ^ mask};
    }
}
