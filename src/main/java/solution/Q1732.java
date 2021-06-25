package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find the Highest Altitude",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-the-highest-altitude/"

)
public class Q1732 {
    public int largestAltitude(int[] gain) {
        int max = 0;
        int sum = 0;
        for(int g : gain) {
            sum += g;
            max = Math.max(sum, max);
        }

        return max;
    }
}
