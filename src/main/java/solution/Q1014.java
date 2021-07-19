package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Best Sightseeing Pair",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/best-sightseeing-pair/"
)
public class Q1014 {
    /**
        for each position, search for the best spot from left
        time: O(N)
        space: O(1)
    */
    public int maxScoreSightseeingPair(int[] values) {
        int n = values.length;
        int maxScore = 0;

        int maxL = 1;
        for(int i = 0; i < n; i++) {
            maxScore = Math.max(maxScore, maxL - 1 + values[i]);
            maxL = Math.max(maxL - 1, values[i]);
        }

        return maxScore;
    }
}
