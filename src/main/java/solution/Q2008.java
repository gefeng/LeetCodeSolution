package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Maximum Earnings From Taxi",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-earnings-from-taxi/"
)
public class Q2008 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public long maxTaxiEarnings(int n, int[][] rides) {
        int m = rides.length;
        long ans = 0;

        Arrays.sort(rides, Comparator.comparingInt(a -> a[1]));

        long[] dp = new long[m];

        for(int i = 0; i < m; i++) {
            int idx = binarySearch(rides, 0, i - 1, rides[i][0]);
            long earn = (long)rides[i][1] - rides[i][0] + rides[i][2];
            if(idx == -1) {
                dp[i] = i == 0 ? earn : Math.max(dp[i - 1], earn);
            } else {
                dp[i] = Math.max(dp[i - 1], dp[idx] + earn);
            }

            ans = Math.max(ans, dp[i]);
        }

        return ans;
    }

    private int binarySearch(int[][] r, int start, int end, int target) {
        int lo = start;
        int hi = end;
        int ans = -1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;

            if(r[mid][1] <= target) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }
}
