package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Element",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY
)
public class Q27 {
    public int removeElement(int[] nums, int val) {
        if(nums.length == 0)
            return 0;
        int i = 0;
        for(int j = 0; j < nums.length; ++j) {
            if(nums[j] != val) {
                nums[i] = nums[j];
                i++;
            }
        }

        return i;
    }
}
