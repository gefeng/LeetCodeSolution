package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Partition Array According to Given Pivot",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/partition-array-according-to-given-pivot/"
)
public class Q2161 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] pivotArray(int[] nums, int pivot) {
        int n = nums.length;
        int[] ans = new int[n];
        int p = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] < pivot) {
                ans[p++] = nums[i];
            }
        }

        for(int i = 0; i < n; i++) {
            if(nums[i] == pivot) {
                ans[p++] = nums[i];
            }
        }

        for(int i = 0; i < n; i++) {
            if(nums[i] > pivot) {
                ans[p++] = nums[i];
            }
        }

        return ans;
    }
}
