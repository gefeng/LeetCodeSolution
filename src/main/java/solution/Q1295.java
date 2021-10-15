package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Numbers with Even Number of Digits",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-numbers-with-even-number-of-digits/"
)
public class Q1295 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int findNumbers(int[] nums) {
        int retVal = 0;
        for(int i = 0; i < nums.length; ++i) {
            int digitsCount = 0;
            int num = nums[i];
            while(num > 0) {
                num = num / 10;
                digitsCount++;
            }
            if(digitsCount % 2 == 0)
                retVal++;
        }
        return retVal;
    }
}
