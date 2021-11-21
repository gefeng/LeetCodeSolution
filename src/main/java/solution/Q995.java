package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of K Consecutive Bit Flips",
        difficulty = QDifficulty.HARD,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-number-of-k-consecutive-bit-flips/"
)
public class Q995 {
    /**
     * Sweep line
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minKBitFlips(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        int[] f = new int[n + 1];

        int cnt = 0;
        int cur = 0;
        for(int i = 0; i < n; i++) {
            cnt += f[i];
            cur = (cnt + nums[i]) % 2;

            if(cur == 0) {
                if(i + k > n) {
                    return -1;
                }
                ans += 1;
                cnt += 1;
                f[i + k] -= 1;
            }
        }

        return ans;
    }
}
