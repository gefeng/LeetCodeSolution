package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Next Permutation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/next-permutation/"
)
public class Q31 {
    /**
     * Use this as a template.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int i = n - 2;
        for(; i >= 0; i--) {
            if(nums[i] < nums[i + 1]) break;
        }

        if(i < 0) {
            reverse(nums, 0, n - 1);
            return;
        }

        int j = n - 1;
        while(nums[j] <= nums[i]) j--;

        swap(nums, i, j);
        reverse(nums, i + 1, n - 1);
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    private void reverse(int[] nums, int st, int ed) {
        for(int l = st, r = ed; l < r; l++, r--) {
            swap(nums, l, r);
        }
    }
}
