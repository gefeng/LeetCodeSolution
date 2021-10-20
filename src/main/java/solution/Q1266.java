package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Time Visiting All Points",
        difficulty = QDifficulty.EASY,
        tag = QTag.GEOMETRY,
        url = "https://leetcode.com/problems/minimum-time-visiting-all-points/"
)
public class Q1266 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minTimeToVisitAllPoints(int[][] points) {
        int ans = 0;
        int n = points.length;

        for(int i = 1; i < n; i++) {
            int dx = Math.abs(points[i][0] - points[i - 1][0]);
            int dy = Math.abs(points[i][1] - points[i - 1][1]);

            ans += Math.min(dx, dy) + Math.max(dx, dy) - Math.min(dx, dy);
        }

        return ans;
    }
}
