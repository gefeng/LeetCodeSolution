package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Line Reflection",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/line-reflection/"
)
public class Q356 {
    public boolean isReflected(int[][] points) {
        return linearTimeSol(points);
    }

    /**
     * Time:  O(N * LogN)
     * Space: O(N)
     * */
    private boolean sortSol(int[][] points) {
        Map<Integer, TreeSet<Integer>> map = new HashMap<>();

        for(int[] p : points) {
            map.computeIfAbsent(p[1], k -> new TreeSet<>()).add(p[0]);
        }

        Set<Integer> seen = new HashSet<>();
        for(TreeSet<Integer> xPos : map.values()) {
            while(!xPos.isEmpty()) {
                if(xPos.size() > 1) {
                    seen.add(xPos.pollFirst() + xPos.pollLast());
                } else {
                    seen.add(xPos.pollFirst() * 2);
                }
                if(seen.size() > 1) {
                    return false;
                }
            }
        }

        return true;
    }

    /**
     * Actually we can find min_x and max_x to identify reflect_p * 2 (min_x + max_x)
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    private boolean linearTimeSol(int[][] points) {
        int minx = Integer.MAX_VALUE;
        int maxx = Integer.MIN_VALUE;
        Set<String> seen = new HashSet<>();

        for(int[] p : points) {
            minx = Math.min(minx, p[0]);
            maxx = Math.max(maxx, p[0]);
            StringBuilder sb = new StringBuilder();
            sb.append(p[0]).append(",").append(p[1]);
            seen.add(sb.toString());
        }

        int sum = minx + maxx;
        for(int[] p : points) {
            StringBuilder sb = new StringBuilder();
            sb.append(sum - p[0]).append(",").append(p[1]);
            if(!seen.contains(sb.toString())) {
                return false;
            }
        }
        return true;
    }
}
