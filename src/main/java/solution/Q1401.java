package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Circle and Rectangle Overlapping",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GEOMETRY,
        url = "https://leetcode.com/problems/circle-and-rectangle-overlapping/"
)
public class Q1401 {
    /**
     * Move the circle to the center of the coordinates (0, 0)
     *
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean checkOverlap(int radius, int x_center, int y_center, int x1, int y1, int x2, int y2) {
        x1 -= x_center;
        x2 -= x_center;
        y1 -= y_center;
        y2 -= y_center;

        // x^2 + y^2 <= r^2 => find min(x^2) and min(y2)
        int minX = x1 * x2 <= 0 ? 0 : Math.min(x1 * x1, x2 * x2);
        int minY = y1 * y2 <= 0 ? 0 : Math.min(y1 * y1, y2 * y2);

        return minX + minY <= radius * radius;
    }
}
