package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Arranging Coins",
        difficulty = QDifficulty.EASY,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/arranging-coins/"
)
public class Q441 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int arrangeCoins(int n) {
        int lo = 1;
        int hi = n;
        int res = 1;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            long x = ((long)mid + 1) * mid / 2;

            if(x <= n) {
                res = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return res;
    }
}
