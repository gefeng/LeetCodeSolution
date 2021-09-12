package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Number of Pairs of Interchangeable Rectangles",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/number-of-pairs-of-interchangeable-rectangles/"
)
public class Q2001 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public long interchangeableRectangles(int[][] rectangles) {
        int n = rectangles.length;
        long res = 0;
        Map<Double, Integer> map = new HashMap<>();

        for(int[] r : rectangles) {
            double ratio = (double)r[0] / r[1];

            map.put(ratio, map.getOrDefault(ratio, 0) + 1);
        }

        for(int cnt : map.values()) {
            res += (long)(cnt - 1) * cnt / 2;
        }

        return res;
    }
}
