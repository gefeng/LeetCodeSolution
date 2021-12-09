package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Problem(
        title = "Walking Robot Simulation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/walking-robot-simulation/"
)
public class Q874 {
    /**
     * Time:  O(M + N)
     * Space: O(M)
     * */
    private static final int[][] DIRECTIONS = new int[][] {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int robotSim(int[] commands, int[][] obstacles) {
        int ans = 0;
        Map<Integer, Set<Integer>> map = new HashMap<>();

        for(int[] ob : obstacles) {
            map.computeIfAbsent(ob[0], k -> new HashSet<>()).add(ob[1]);
        }

        int d = 0;
        int x = 0;
        int y = 0;
        for(int c : commands) {
            if(c == -2) {
                d = (d - 1 + 4) % 4;
            } else if(c == -1) {
                d = (d + 1) % 4;
            } else {
                for(int i = 1; i <= c; i++) {
                    int nx = x + DIRECTIONS[d][0];
                    int ny = y + DIRECTIONS[d][1];
                    if(map.containsKey(nx) && map.get(nx).contains(ny)) {
                        break;
                    }
                    x = nx;
                    y = ny;
                }
                ans = Math.max(ans, x * x + y * y);
            }
        }

        return ans;
    }
}
