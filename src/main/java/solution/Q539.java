package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Minimum Time Difference",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/minimum-time-difference/"
)
public class Q539 {
    /**
     * Can be further optimized with counting sort since max time in minutes is 24 * 60.
     *
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int findMinDifference(List<String> timePoints) {
        int n = timePoints.size();
        int res = Integer.MAX_VALUE;
        int[] t = new int[n];

        for(int i = 0; i < n; i++) {
            t[i] = toInt(timePoints.get(i));
        }

        Arrays.sort(t);

        res = 24 * 60 - t[n - 1] + t[0];
        for(int i = 1; i < n; i++) {
            res = Math.min(res, t[i] - t[i - 1]);
        }

        return res;
    }

    private int toInt(String time) {
        return Integer.parseInt(time.substring(0, 2)) * 60 + Integer.parseInt(time.substring(3, 5));
    }
}
