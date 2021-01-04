package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Teemo Attacking",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/teemo-attacking/"
)
public class Q495 {
    public int findPoisonedDuration(int[] timeSeries, int duration) {
        if(timeSeries.length == 0)
            return 0;

        int ans = 0;
        int prevAttack = timeSeries[0];

        for(int i = 1; i < timeSeries.length; i++) {
            int diff = timeSeries[i] - prevAttack;
            if(diff >= duration) {
                ans += duration;
            }
            else {
                ans += diff;
            }

            prevAttack = timeSeries[i];
        }

        return ans + duration;
    }
}
