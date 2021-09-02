package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Counting Bits",
        difficulty = QDifficulty.EASY,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/counting-bits/"
)
public class Q338 {
    /**
     * Achieve linear time by using dp.
     * state:
     *  dp[i] denotes pop count for i.
     * transition:
     *  dp[i] = dp[i - x] + 1
     *  take care of numbers in range [0], [1, 1], [2, 3], [4, 7], [8, 15]...
     *  we can get the result from the previous range and x is the size of the
     *  current range.
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] countBits(int n) {
        int[] cnt = new int[n + 1];
        int l = 1;
        int r = 1;
        while(l <= n) {
            int offset = r - l + 1;
            for(int i = l; i <= r && i <= n; i++) {
                cnt[i] = cnt[i - offset] + 1;
            }

            l = r + 1;
            r = r * 2 + 1;
        }

        return cnt;
    }
}
