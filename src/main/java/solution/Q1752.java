package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if Array Is Sorted and Rotated",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/check-if-array-is-sorted-and-rotated/"
)
public class Q1752 {
    public boolean check(int[] nums) {
        return checkNeighbors(nums);
    }

    private boolean checkOneByOne(int[] nums) {
        int n = nums.length;
        int min = nums[0];
        int max = nums[n - 1];

        int i = 1;
        while(i < n && nums[i] >= nums[i - 1]) {
            i++;

        }

        if(i == n) {
            return true;
        }

        i++;
        while(i < n && nums[i] >= nums[i - 1]) {
            i++;
        }

        return i == n && min >= max;
    }

    private boolean checkNeighbors(int[] nums) {
        int n = nums.length;
        int k = 0;
        for(int i = 0; i < n; i++) {
            int next = (i + 1) % n;
            if(nums[i] > nums[(i + 1) % n]) {
                k++;
            }
            if(k > 1) {
                return false;
            }
        }
        return true;
    }
}
