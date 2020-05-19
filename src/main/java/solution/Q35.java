package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Search Insert Position",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY
)
public class Q35 {
    public int searchInsert(int[] nums, int target) {
        int i = 0;
        for(int j = 0; j < nums.length; ++j) {
            if(target > nums[j]) {
                i++;
            } else
                break;
        }
        return i;
    }
}
