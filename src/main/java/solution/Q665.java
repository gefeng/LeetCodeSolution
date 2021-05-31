package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Non-decreasing Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/non-decreasing-array/"
)
public class Q665 {
    /*
    * 看着简单，其实不简单的一道题。。。
    * 需要考虑如下三种种情况，
    * 1. [..2,1,4...] 这种比较直接，直接跳过即可
    * 2. [..5,2,4...] 这种比较麻烦，只能替换5，但替换5有可能会破坏之前的递增序列 比如 [3,5,2,4] 这种情况是false的
    *    需要在往前比较一位nums[i - 2]和nums[i]
    * 3. 首尾递减都可以直接替换当前值，所以直接跳过和第一种一样
    * */
    public boolean checkPossibility(int[] nums) {
        boolean skip = false;
        for(int i = 1; i < nums.length; i++) {
            if(nums[i] < nums[i - 1]) {
                if(skip) {
                    return false;
                }

                if(i == 1 || i == nums.length - 1 || nums[i + 1] >= nums[i - 1]) {
                    skip = true;
                } else {
                    if(nums[i] < nums[i - 2]) {
                        return false;
                    } else {
                        skip = true;
                    }
                }
            }
        }

        return true;
    }
}
