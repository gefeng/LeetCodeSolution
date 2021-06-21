package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Nearest Point That Has the Same X or Y Coordinate",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-nearest-point-that-has-the-same-x-or-y-coordinate/"
)
public class Q1779 {
    public int nearestValidPoint(int x, int y, int[][] points) {
        int minDist = Integer.MAX_VALUE;
        int minIdx = -1;
        for(int i = 0; i < points.length; i++) {
            int[] p = points[i];

            if(x == p[0] || y == p[1]) {
                int dist = Math.abs(x - p[0]) + Math.abs(y - p[1]);
                if(dist < minDist) {
                    minDist = dist;
                    minIdx = i;
                }
            }
        }

        return minIdx;
    }
}
