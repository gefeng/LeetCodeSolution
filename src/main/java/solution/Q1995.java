package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Special Quadruplets",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/count-special-quadruplets/"
)
public class Q1995 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(1)
     * */
    public int countQuadruplets(int[] nums) {
        int res = 0;
        int n = nums.length;

        for(int i = 0; i < n - 3; i++) {
            for(int j = i + 1; j < n - 2; j++) {
                res += twoSum(nums, j + 1, nums[i] + nums[j]);
            }
        }

        return res;
    }

    /**
     * nums[d] - nums[c] == target
     * nums[c] = target - nums[d]
     * */
    private int twoSum(int[] nums, int start, int target) {
        int n = nums.length;
        int res = 0;
        int[] cnt = new int[101];
        for(int i = start; i < n; i++) {
            int x = nums[i] - target;
            if(x > 0 && cnt[x] != 0) {
                res += cnt[x];
            }
            cnt[nums[i]]++;
        }
        return res;
    }
}
