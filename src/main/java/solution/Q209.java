package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Size Subarray Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-size-subarray-sum/"
)
public class Q209 {
    public int minSubArrayLen(int s, int[] nums) {
        int begin = 0;
        int sum = 0;
        int minLen = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++) {
            sum += nums[i];
            while(sum >= s) {
                minLen = Math.min(minLen, i - begin + 1);
                sum -= nums[begin++];
            }
        }

        return minLen ==  Integer.MAX_VALUE ? 0 : minLen;
    }
}
