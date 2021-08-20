package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Number of Boomerangs",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/number-of-boomerangs/"
)
public class Q447 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int numberOfBoomerangs(int[][] points) {
        int n = points.length;
        int res = 0;

        for(int i = 0; i < n; i++) {
            Map<Integer, Integer> distMap = new HashMap<>();
            for(int j = 0; j < n; j++) {
                int d = getDist(points[i], points[j]);
                distMap.put(d, distMap.getOrDefault(d, 0) + 1);
            }

            for(int cnt : distMap.values()) {
                res += cnt * (cnt - 1);
            }
        }


        return res;
    }

    private int getDist(int[] p1, int[] p2) {
        return (p1[0] - p2[0]) * (p1[0] - p2[0]) + (p1[1] - p2[1]) * (p1[1] - p2[1]);
    }
}
