package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Diet Plan Performance",
        difficulty = QDifficulty.EASY,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/diet-plan-performance/"
)
public class Q1176 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int dietPlanPerformance(int[] calories, int k, int lower, int upper) {
        int points = 0;
        int sum = 0;
        for(int l = 0, r = 0; r < calories.length; r++) {
            sum += calories[r];
            if(r - l == k - 1) {
                if(sum < lower)
                    points--;
                else if(sum > upper)
                    points++;

                sum -= calories[l++];
            }
        }
        return points;
    }
}
