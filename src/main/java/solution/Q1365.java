package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "How Many Numbers Are Smaller Than the Current Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY
)
public class Q1365 {
    public int[] smallerNumbersThanCurrent(int[] nums) {
        int[] retVal = new int[nums.length];
        int[] freq = new int[101];

        for(int i = 0; i < nums.length; ++i) {
            freq[nums[i]]++;
        }

        for(int i = 0; i < nums.length; ++i) {
            int count = 0;
            for(int j = 0; j < nums[i]; ++j) {
                count += freq[j];
            }
            retVal[i] = count;
        }
        return retVal;
    }
}
