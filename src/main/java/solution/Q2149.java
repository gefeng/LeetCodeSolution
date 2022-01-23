package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Rearrange Array Elements by Sign",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/rearrange-array-elements-by-sign/"
)
public class Q2149 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] rearrangeArray(int[] nums) {
        int n =  nums.length;
        int[] ans = new int[n];

        int p = 0;
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                ans[p] = nums[i];
                p += 2;
            }
        }

        p = 1;
        for(int i = 0; i < n; i++) {
            if(nums[i] < 0) {
                ans[p] = nums[i];
                p += 2;
            }
        }

        return ans;
    }
}
