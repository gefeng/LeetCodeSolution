package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Preimage Size of Factorial Zeroes Function",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/preimage-size-of-factorial-zeroes-function/"
)
public class Q793 {
    /**
     * Time:  O(logK * logK)
     * Space: O(1)
     * */
    private static final long MAX = (long)1e10;
    public int preimageSizeFZF(int k) {
        return helper(k + 1) - helper(k);
    }

    private int helper(int k) {
        long lo = 0;
        long hi = MAX;
        long ans = -1;

        while(lo <= hi) {
            long mid = lo + hi >> 1;

            if(cntZero(mid) >= k) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return (int)ans;
    }

    private long cntZero(long x) {
        long cnt = 0;
        while(x > 0) {
            cnt += x / 5;
            x /= 5;
        }

        return cnt;
    }
}
