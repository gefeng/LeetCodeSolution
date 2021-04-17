package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sort Colors",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/sort-colors/"
)
public class Q75 {
    public void sortColors(int[] nums) {
        threePointers(nums);
    }

    private void countingSort(int[] nums) {
        int[] count = new int[3];
        for(int n : nums)
            count[n]++;

        int i = 0;
        while(i < nums.length) {
            for(int k = 0; k < count.length; k++) {
                for(int j = 0; j < count[k]; j++) {
                    nums[i + j] = k;
                }
                i += count[k];
            }
        }
    }

    /*
    * 这个鬼算法谁想得到, 非要one pass
    * */
    private void threePointers(int[] nums) {
        int i = 0;
        int r = 0;
        int b = nums.length - 1;
        while(i <= b) {
            if(nums[i] == 0) {
                swap(nums, i, r);
                r++;
                i++;
            } else if(nums[i] == 2) {
                swap(nums, i, b);
                b--;
            } else {
                i++;
            }
        }
    }

    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
