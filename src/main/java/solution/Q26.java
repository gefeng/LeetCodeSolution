package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Duplicates from Sorted Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/remove-duplicates-from-sorted-array/"
)
public class Q26 {
    public int removeDuplicates(int[] nums) {
        if(nums.length == 0)
            return 0;
        int i = 0;
        for(int j = 1; j < nums.length; ++j) {
            if(nums[i] != nums[j]) {
                i++;
                nums[i] = nums[j];
            }
        }
        return i + 1;
    }
}
