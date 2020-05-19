package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Create Target Array in the Given Order",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/create-target-array-in-the-given-order/"
)
public class Q1389 {
    public int[] createTargetArray(int[] nums, int[] index) {
        int[] retVal = new int[nums.length];
        for(int i = 0; i < nums.length; ++i) {
            for(int j = i; j > index[i]; --j) {
                retVal[j] = retVal[j - 1];
            }
            retVal[index[i]] = nums[i];
        }
        return retVal;
    }
}
