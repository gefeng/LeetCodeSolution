package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Number of Darts Inside of a Circular Dartboard",
        difficulty = QDifficulty.HARD,
        tag = QTag.GEOMETRY,
        url = "https://leetcode.com/problems/maximum-number-of-darts-inside-of-a-circular-dartboard/"
)
public class Q1453 {
    /**
     * Geometry problem. Copied function to calculate center by given 2 points and radius.
     *
     * Time:  O(N ^ 3)
     * Space: O(1)
     * */
    private static final double EXP = 1e-9;
    public int numPoints(int[][] points, int r) {
        int n = points.length;
        int ans = 1;

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                int[] p1 = points[i];
                int[] p2 = points[j];
                if(p1[0] == p2[0] && p1[1] == p2[1]) {
                    continue;
                }
                if(getDist(p1[0], p1[1], p2[0], p2[1]) > 4 * r * r) {
                    continue;
                }

                double[] c = getCenter(p1, p2, r);

                int cnt = 0;
                for(int[] p : points) {
                    if(getDist(p[0], p[1], c[0], c[1]) <= r * r + EXP) {
                        cnt++;
                    }
                }
                ans = Math.max(ans, cnt);
            }
        }

        return ans;
    }

    private double[] getCenter(int[] p1, int[] p2, int r) {
        double mx = (p1[0] + p2[0]) / 2.0;
        double my = (p1[1] + p2[1]) / 2.0;
        double dirX = p1[0] - p2[0];
        double dirY = p1[1] - p2[1];
        double sqr = Math.sqrt(dirX * dirX + dirY * dirY);
        dirX /= sqr;
        dirY /= sqr;
        double h = Math.sqrt(r * r - sqr / 2 * sqr / 2);
        double moveX = -dirY;
        double moveY = dirX;

        double centerX = mx + moveX * h;
        double centerY = my + moveY * h;

        return new double[] { centerX, centerY };
    }

    private double getDist(double x1, double y1, double x2, double y2) {
        return (x1 - x2) * (x1 - x2) + (y1 - y2) * (y1 - y2);
    }
}
