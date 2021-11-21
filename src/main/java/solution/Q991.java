package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Broken Calculator",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/broken-calculator/"
)
public class Q991 {
    /**
     * Time:  O(log(target))
     * Space: O(1)
     * */
    public int brokenCalc(int startValue, int target) {
        int ans = 0;
        int x = startValue;
        int y = target;
        while(x < y) {
            if(y % 2 == 0) {
                y = y / 2;
                ans += 1;
            } else {
                y = (y + 1) / 2;
                ans += 2;
            }
        }

        return ans + x - y;
    }
}
