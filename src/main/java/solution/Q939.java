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
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int minAreaRect(int[][] points) {
        int ans = Integer.MAX_VALUE;
        int n = points.length;

        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int[] p : points) {
            map.computeIfAbsent(p[0], k -> new HashSet<>()).add(p[1]);
        }

        for(int[] p1 : points) {
            for(int[] p2 : points) {
                // fix top right and bottom left
                if(p1[0] != p2[0] && p1[1] != p2[1]) {
                    if(map.get(p1[0]).contains(p2[1]) && map.get(p2[0]).contains(p1[1])) {
                        ans = Math.min(ans, Math.abs(p1[0] - p2[0]) * Math.abs(p1[1] - p2[1]));
                    }
                }
            }
        }

        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
