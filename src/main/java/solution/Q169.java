package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Majority Element",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/majority-element/"
)
public class Q169 {
    public int majorityElement(int[] nums) {
        int candidate = 0;
        int count = 0;

        for(int n : nums) {
            if(candidate == n) {
                count++;
            } else if(count > 0) {
                count--;
            } else if(count == 0) {
                candidate = n;
                count = 1;
            }
        }

        return candidate;
    }
}
