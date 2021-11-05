package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check If a Number Is Majority Element in a Sorted Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/check-if-a-number-is-majority-element-in-a-sorted-array/"
)
public class Q1150 {
    public boolean isMajorityElement(int[] nums, int target) {
        int i = 0;
        int n = nums.length;

        while(i < n && nums[i] != target) {
            i++;
        }

        if(i == n) {
            return false;
        }

        int j = i;
        while(j < n && nums[j] == target) {
            j++;
        }

        return (j - i) * 2 > n;
    }
}
