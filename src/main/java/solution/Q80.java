package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Duplicates from Sorted Array II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/remove-duplicates-from-sorted-array-ii/"
)
public class Q80 {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        int i = 0;
        int j = 0;
        int cnt = 0;
        int prev = 0;
        for(; i < n; i++) {
            if(i > 0 && nums[i] == prev) {
                cnt++;
            } else {
                cnt = 1;
            }

            prev = nums[i];

            if(cnt < 3) {
                swap(nums, i, j);
                j++;
            }
        }

        return j;
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
