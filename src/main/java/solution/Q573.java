package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Squirrel Simulation",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/squirrel-simulation/"
)
public class Q573 {
    /**
     * This is NOT a BFS problem. It's just pure math.
     * Calculate manhattan distance and find the minimum.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minDistance(int height, int width, int[] tree, int[] squirrel, int[][] nuts) {
        int sum = 0;
        int res = Integer.MAX_VALUE;

        for(int[] nut : nuts) {
            sum += 2 * getManhattanDist(tree, nut);
        }

        for(int[] nut : nuts) {
            res = Math.min(res, sum - getManhattanDist(tree, nut) + getManhattanDist(squirrel, nut));
        }

        return res;
    }

    private int getManhattanDist(int[] x, int[] y) {
        return Math.abs(x[0] - y[0]) + Math.abs(x[1] - y[1]);
    }
}
