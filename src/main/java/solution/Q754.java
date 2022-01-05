package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reach a Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/reach-a-number/"
)
public class Q754 {
    /**
     * Time:  O(logT)
     * Space: O(1)
     * */
    public int reachNumber(int target) {
        int i = 1;
        int sum = 0;
        int t = Math.abs(target);

        while(true) {
            sum += i;

            if(sum >= t) {
                if((sum - t) % 2 == 0) return i;
            }

            i++;
        }
    }
}
