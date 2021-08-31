package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Patching Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/patching-array/"
)
public class Q330 {
    /**
     * Time:  O(M + logN)
     * Space: O(1)
     * */
    public int minPatches(int[] nums, int n) {
        int res = 0;
        long[] range = new long[] {1, 1};

        for(int i = 0; i < nums.length; i++) {
            while(range[1] < nums[i] && range[1] <= n) {
                res++;
                range[1] += range[1];
            }

            if(range[1] >= n) {
                return res;
            }

            range[1] = Math.max(nums[i] * 2, range[1] + nums[i]);
        }

        while(range[1] < n) {
            res++;
            range[1] *= 2;
        }

        return res;
    }
}
