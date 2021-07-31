package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Problem(
        title = "Maximum Number of Visible Points",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/maximum-number-of-visible-points/"
)
public class Q1610 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int visiblePoints(List<List<Integer>> points, int angle, List<Integer> location) {
        int n = points.size();
        int res = 0;
        int cnt = 0;
        List<Double> angles = new ArrayList<>();

        for(List<Integer> p : points) {
            int x1 = p.get(0);
            int y1 = p.get(1);
            int x2 = location.get(0);
            int y2 = location.get(1);
            if(x1 == x2 && y1 == y2) {
                cnt++;
            } else {
                angles.add(getAngles(x1, y1, x2, y2));
            }
        }

        Collections.sort(angles);

        int sz = angles.size();
        for(int l = 0, r = 0; r < sz * 2; r++) {
            while((r < sz ? angles.get(r) : angles.get(r % sz) + 360) -
                    (l < sz ? angles.get(l) : angles.get(l % sz) + 360) > angle) {
                l++;
            }

            res = Math.max(res, r - l + 1);
        }

        return res + cnt;
    }

    private double getAngles(int x1, int y1, int x2, int y2) {
        double angle = Math.toDegrees(Math.atan2(y1 - y2, x1 - x2));
        return angle < 0 ? angle + 360 : angle;
    }
}
