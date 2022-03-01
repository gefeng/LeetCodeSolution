package solution.weekly282;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Time to Complete Trips",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/contest/weekly-contest-282/problems/minimum-time-to-complete-trips/"
)
public class Q2187 {
    /**
     * Time:  O(N * logN)
     * Space: O(1)
     * */
    public long minimumTime(int[] time, int totalTrips) {
        long lo = 1;
        long hi = (long)1e15;
        long ans = 1;

        while(lo <= hi) {
            long mid = lo + hi >> 1;

            long tot = 0;
            boolean isOk = false;
            for(int t : time) {
                tot += mid / (long)t;
                if(tot >= totalTrips) {
                    isOk = true;
                    break;
                }
            }

            if(isOk) {
                ans = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return ans;
    }
}
