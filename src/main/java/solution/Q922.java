package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sort Array By Parity II",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/sort-array-by-parity-ii/"
)
public class Q922 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int[] sortArrayByParityII(int[] nums) {
        int n = nums.length;

        int i = 0, j = 1;
        while(i < n && j < n) {
            if(nums[i] % 2 == 1 || nums[j] % 2 == 0) {
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
            }
            if(nums[i] % 2 == 0) {
                i += 2;
            }
            if(nums[j] % 2 == 1) {
                j += 2;
            }
        }

        return nums;
    }
}
