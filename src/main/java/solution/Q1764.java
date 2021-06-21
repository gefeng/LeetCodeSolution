package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Form Array by Concatenating Subarrays of Another Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/form-array-by-concatenating-subarrays-of-another-array/"
)
public class Q1764 {
    /*
    * find one by one
    * */
    public boolean canChoose(int[][] groups, int[] nums) {
        return search(groups, nums, 0, 0);
    }

    private boolean search(int[][] groups, int[] nums, int currGroup, int currNum) {
        if(currGroup == groups.length) {
            return true;
        }
        if(currNum > nums.length - 1) {
            return false;
        }

        int index = findGroup(nums, groups[currGroup], currNum);

        if(index == -1) {
            return false;
        }

        return search(groups, nums, currGroup + 1, index + groups[currGroup].length);
    }

    private int findGroup(int[] nums, int[] target, int start) {
        int n = nums.length;

        for(int i = start; i < n; i++) {
            if(target[0] != nums[i]) {
                continue;
            }

            boolean same = true;
            for(int j = 0; j < target.length; j++) {
                if(i + j > n - 1 || target[j] != nums[i + j]) {
                    same = false;
                    break;
                }
            }

            if(same) {
                return i;
            }
        }

        return -1;
    }
}
