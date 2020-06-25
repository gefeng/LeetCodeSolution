package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Pivot Index",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-pivot-index/"
)
public class Q724 {
    public int pivotIndex(int[] nums) {
        if(nums.length == 0)
            return -1;

        int sum = 0;
        int sumLeft = 0;
        for(int n : nums)
            sum += n;

        for(int i = 0; i < nums.length; i++) {
            if((sumLeft * 2 + nums[i]) == sum)
                return i;
            sumLeft += nums[i];
        }
        return -1;
    }
}
