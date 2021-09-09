package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Last Moment Before All Ants Fall Out of a Plank",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BRAINTEASER,
        url = "https://leetcode.com/problems/last-moment-before-all-ants-fall-out-of-a-plank/"
)
public class Q1503 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int getLastMoment(int n, int[] left, int[] right) {
        int res = 0;

        for(int p : left) {
            res = Math.max(res, p);
        }

        for(int p : right) {
            res = Math.max(res, n - p);
        }

        return res;
    }
}
