package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Generate Random Point in a Circle",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/generate-random-point-in-a-circle/"
)
public class Q478 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    private double r;
    private double cx;
    private double cy;
    public Q478(double radius, double x_center, double y_center) {
        this.r = radius;
        this.cx = x_center;
        this.cy = y_center;
    }

    public double[] randPoint() {
        double rr = Math.sqrt(Math.random()) * r;
        double rt = Math.random() * 2 * Math.PI;
        double rx = Math.sin(rt) * rr + cx;
        double ry = Math.cos(rt) * rr + cy;

        return new double[] {rx, ry};
    }
}
