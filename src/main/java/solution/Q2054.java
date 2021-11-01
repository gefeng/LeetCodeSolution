package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Two Best Non-Overlapping Events",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/two-best-non-overlapping-events/"
)
public class Q2054 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int maxTwoEvents(int[][] events) {
        int ans = 0;
        int n = events.length;

        int[] dp = new int[n];

        Arrays.sort(events, Comparator.comparingInt(a -> a[1]));

        for(int i = 0; i < n; i++) {
            int[] e =  events[i];
            int idx = binarySearch(events, i - 1, e);

            if(idx >= 0) {
                ans = Math.max(dp[idx] + e[2], ans);
            } else {
                ans = Math.max(ans, e[2]);
            }

            dp[i] = i == 0 ? e[2] : Math.max(e[2], dp[i - 1]);
        }

        return ans;
    }

    private int binarySearch(int[][] events, int hi, int[] e) {
        int lo = 0;
        int ans = -1;
        while(lo <= hi) {
            int mid = lo + hi >> 1;

            if(events[mid][1] < e[0]) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }
        return ans;
    }
}
