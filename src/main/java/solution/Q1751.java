package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Number of Events That Can Be Attended II",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended-ii/"
)
public class Q1751 {
    public int maxValue(int[][] events, int k) {
        Arrays.sort(events, (a, b) -> Integer.compare(a[0], b[0]));

        return dfs(events, 0, k, new Integer[events.length][k + 1]);
    }

    // time : O(N * K * logN) space: O(N * K)
    private int dfs(int[][] events, int i, int k, Integer[][] memo) {
        if(k == 0 || i == events.length) {
            return 0;
        }

        if(memo[i][k] != null) {
            return memo[i][k];
        }

        return memo[i][k] = Math.max(dfs(events, i + 1, k, memo),
                dfs(events, next(events, i), k - 1, memo) + events[i][2]);
    }

    private int next(int[][] events, int i) {
        int lo = i + 1;
        int hi = events.length - 1;
        int next = events.length;

        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if(events[mid][0] > events[i][1]) {
                next = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return next;
    }
}
