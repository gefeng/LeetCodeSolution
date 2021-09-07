package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Best Position for a Service Centre",
        difficulty = QDifficulty.HARD,
        tag = QTag.GEOMETRY,
        url = "https://leetcode.com/problems/best-position-for-a-service-centre/"
)
public class Q1515 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public double getMinDistSum(int[][] positions) {
        double res = Double.MAX_VALUE;
        double cx = 50.0, cy = 50.0, r = 50.0;
        double minX = 0.0, minY = 0.0;

        while(r >= 0.001) {
            double delta = r / 100.0;
            for(double x = cx - r; x <= cx + r; x += delta) {
                for(double y = cy - r; y <= cy + r; y += delta) {
                    double sum = 0.0;
                    for(int[] p : positions) {
                        sum += getDist(x, y, p[0], p[1]);
                    }

                    if(sum < res) {
                        res = sum;
                        minX = x;
                        minY = y;
                    }
                }
            }

            cx = minX;
            cy = minY;
            r /= 100.0;
        }

        return res;
    }

    private double getDist(double x1, double y1, double x2, double y2) {
        return Math.sqrt((x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2));
    }
}
