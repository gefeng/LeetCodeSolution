package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Middle Index in Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-the-middle-index-in-array/"
)
public class Q1991 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int findMiddleIndex(int[] nums) {
        int n = nums.length;
        int res = -1;
        int[] preSum = new int[n + 1];

        for(int i = 1; i < n + 1; i++) {
            preSum[i] = preSum[i - 1] + nums[i - 1];
        }

        for(int i = 1; i < n + 1; i++) {
            int l = preSum[i - 1];
            int r = preSum[n] - preSum[i];
            if(l == r) {
                res = i - 1;
                break;
            }
        }

        return res;
    }
}
