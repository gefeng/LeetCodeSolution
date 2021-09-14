package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Shuffle the Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/shuffle-the-array/"
)
public class Q1470 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] shuffle(int[] nums, int n) {
        int[] ans = new int[2 * n];

        for(int i = 0; i < n; i++) {
            ans[2 * i] = nums[i];
            ans[2 * i + 1] = nums[n + i];
        }

        return ans;
    }
}
