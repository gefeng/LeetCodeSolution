package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Problem(
        title = "Minimum Area Rectangle",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/minimum-area-rectangle/"
)
public class Q939 {
    /**
     * Instead of using Map<Integer, Set<Integer>> to build lookup table,
     * we can just use a Set<Long> where for each 64bits, left most 32bits
     * is for x and right most 32bits for y.
     *
     * This is a slightly improvement on run time since map is more expensive.
     *
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int minAreaRect(int[][] points) {
        int ans = Integer.MAX_VALUE;
        int n = points.length;

        Set<Long> set = new HashSet<>();

        for(int[] p : points) {
            set.add((long)p[0] << 32 | p[1]);
        }

        for(int[] p1 : points) {
            for(int[] p2 : points) {
                // fix top right and bottom left
                if(p1[0] != p2[0] && p1[1] != p2[1]) {
                    if(set.contains((long)p1[0] << 32 | p2[1]) && set.contains((long)p2[0] << 32 | p1[1])) {
                        ans = Math.min(ans, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
