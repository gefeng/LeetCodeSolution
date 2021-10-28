package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Cost to Move Chips to The Same Position",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-cost-to-move-chips-to-the-same-position/"
)
public class Q1217 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minCostToMoveChips(int[] position) {
        int ans = Integer.MAX_VALUE;
        int n = position.length;

        int cntOdd = 0;
        int cntEve = 0;
        for(int p : position) {
            if(p % 2 == 0) {
                cntEve += 1;
            } else {
                cntOdd += 1;
            }
        }

        return Math.min(cntEve, cntOdd);
    }
}
