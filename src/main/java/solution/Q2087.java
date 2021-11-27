package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Cost Homecoming of a Robot in a Grid",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-cost-homecoming-of-a-robot-in-a-grid/"
)
public class Q2087 {
    /**
     * Let's solve it using classic dijkstra and get a BIG GREEN ACCEPTED.
     * DenDen, gotcha ya, didn't check constraints? Does it lead to TLE?
     *
     * Time:  O(M + N)
     * Space: O(1)
     * */
    public int minCost(int[] startPos, int[] homePos, int[] rowCosts, int[] colCosts) {
        int r = startPos[0];
        int c = startPos[1];
        int tr = homePos[0];
        int tc = homePos[1];
        int ans = 0;

        int d = tr >= r ? 1 : -1;
        while(r != tr) {
            r += d;
            ans += rowCosts[r];
        }

        d = tc >= c ? 1 : -1;
        while(c != tc) {
            c += d;
            ans += colCosts[c];
        }

        return ans;
    }
}
