package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Widest Vertical Area Between Two Points Containing No Points",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/widest-vertical-area-between-two-points-containing-no-points/"
)
public class Q1637 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int maxWidthOfVerticalArea(int[][] points) {
        int res = 0;
        int n = points.length;

        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));

        for(int i = 1; i < n; i++) {
            res = Math.max(res, points[i][0] - points[i - 1][0]);
        }

        return res;
    }
}
