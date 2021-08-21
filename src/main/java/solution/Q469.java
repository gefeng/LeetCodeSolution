package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.List;

@Problem(
        title = "Convex Polygon",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GEOMETRY,
        url = "https://leetcode.com/problems/convex-polygon/"
)
public class Q469 {
    /**
     * Check cross product. A convex should have all positive or negative
     * cross products between any two consecutive edges.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean isConvex(List<List<Integer>> points) {
        int n = points.size();
        int sign = 0;
        for(int i = 0; i < n; i++) {
            List<Integer> y = points.get(i);
            List<Integer> x = points.get(i == 0 ? n - 1 : i - 1);
            List<Integer> z = points.get(i == n - 1 ? 0 : i + 1);

            int[] v1 = new int[] { y.get(0) - x.get(0), y.get(1) - x.get(1) };
            int[] v2 = new int[] { z.get(0) - y.get(0), z.get(1) - y.get(1) };
            int crossProd = v1[0] * v2[1] - v1[1] * v2[0];

            if(sign == 0) {
                sign = crossProd < 0 ? -1 : 1;
            } else {
                if(sign * crossProd < 0) {
                    return false;
                }
            }
        }

        return true;
    }
}
