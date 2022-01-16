package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Moves to Reach Target Score",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-moves-to-reach-target-score/"
)
public class Q2139 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int minMoves(int target, int maxDoubles) {
        int ans = 0;
        while(target > 0 && maxDoubles > 0) {
            if(target % 2 == 1) {
                target--;
            } else {
                maxDoubles--;
                target /= 2;
            }
            ans++;
        }

        return ans + target - 1;
    }
}
