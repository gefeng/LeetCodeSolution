package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Running Time of N Computers",
        difficulty = QDifficulty.HARD,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/maximum-running-time-of-n-computers/"
)
public class Q2141 {
    /**
     * Time:  O(N * logN)
     * Space: O(1)
     * */
    public long maxRunTime(int n, int[] batteries) {
        long lo = 0;
        long hi = (long)1e14;
        long ans = 0;

        while(lo <= hi) {
            long mid = lo + hi >> 1;

            long extra = 0;
            long cnt = 0;
            for(int b : batteries) {
                if(b >= mid) {
                    cnt++;
                } else {
                    extra += b; // not enough power to run pc for current candidate time.
                }
            }

            boolean isOk = false;
            if(cnt >= n) isOk = true;   // this is obviouse since we have "strong" batteries to power every pc within candidate time.
            else isOk = mid * (n - cnt) <= extra; // we have some pc left and some extra power (all batteries left can be treated as a single one big battery and should be evenly used to power left PCs`)

            if(isOk) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }
}
