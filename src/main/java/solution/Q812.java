package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Triangle Area",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/largest-triangle-area/"
)
public class Q812 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(1)
     * */
    public double largestTriangleArea(int[][] points) {
        double ans = 0;
        int n = points.length;

        for(int i = 0; i < n; i++) {
            for(int j = i + 1; j < n; j++) {
                for(int k = j + 1; k < n; k++) {
                    ans = Math.max(ans, area(points[i], points[j], points[k]));
                }
            }
        }

        return ans;
    }

    private double area(int[] x, int[] y, int[] z) {
        double a = dist(x, y);
        double b = dist(x, z);
        double c = dist(y, z);
        double s = (a + b + c) / 2;

        if(s - a < 0 || s - b < 0 || s - c < 0) {
            return 0;
        }

        return Math.sqrt(s * (s - a) * (s - b) * (s - c));
    }

    private double dist(int[] x, int[] y) {
        return Math.sqrt((x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]));
    }
}
