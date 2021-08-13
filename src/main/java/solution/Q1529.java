package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Bulb Switcher IV",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/bulb-switcher-iv/"
)
public class Q1529 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minFlips(String target) {
        int res = 0;
        int n = target.length();
        int state = 0;

        for(int i = 0; i < n; i++) {
            int c = target.charAt(i) - '0';
            if(c != state) {
                state = c;
                res++;
            }
        }

        return res;
    }
}
