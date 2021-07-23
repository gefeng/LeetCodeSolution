package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Get Maximum in Generated Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/get-maximum-in-generated-array/"
)
public class Q1646 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int getMaximumGenerated(int n) {
        if(n < 2) {
            return n;
        }

        int[] nums = new int[n + 1];
        nums[1] = 1;
        int ans = 0;
        for(int i = 2; i < n + 1; i++) {
            if(i % 2 == 0) {
                nums[i] = nums[i / 2];
            } else {
                nums[i] = nums[i / 2] + nums[i / 2 + 1];
            }
            ans = Math.max(ans, nums[i]);
        }

        return ans;
    }
}
