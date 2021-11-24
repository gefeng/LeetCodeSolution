package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Problem(
        title = "Minimum Area Rectangle II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GEOMETRY,
        url = "https://leetcode.com/problems/minimum-area-rectangle-ii/"
)
public class Q963 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N)
     * */
    public double minAreaFreeRect(int[][] points) {
        double ans = Double.MAX_VALUE;
        int n = points.length;
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int[] p : points) {
            map.computeIfAbsent(p[0], k -> new HashSet<>()).add(p[1]);
        }

        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                if(i == j) {
                    continue;
                }

                for(int k = 0; k < n; k++) {
                    if(k == i || k == j) {
                        continue;
                    }

                    int[] v1 = new int[] {points[i][0] - points[j][0], points[i][1] - points[j][1]};
                    int[] v2 = new int[] {points[k][0] - points[j][0], points[k][1] - points[j][1]};

                    if(dot(v1, v2) == 0) {
                        int dx = points[j][0] - points[i][0];
                        int dy = points[j][1] - points[i][1];
                        int x = points[k][0] - dx;
                        int y = points[k][1] - dy;
                        if(map.containsKey(x) && map.get(x).contains(y)) {
                            ans = Math.min(ans, getDist(points[i], points[j]) * getDist(points[k], points[j]));
                        }
                    }
                }
            }
        }

        return ans == Double.MAX_VALUE ? 0 : ans;
    }

    private double getDist(int[] x, int[] y) {
        return Math.sqrt((x[0] - y[0]) * (x[0] - y[0]) + (x[1] - y[1]) * (x[1] - y[1]));
    }

    private int dot(int[] v1, int[] v2) {
        return v1[0] * v2[0] + v1[1] * v2[1];
    }
}
