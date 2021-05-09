package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Bulb Switcher",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/bulb-switcher/"
)
public class Q319 {
    /*
    * 只有位置是在perfect square上的灯才是亮的
    * */
    public int bulbSwitch(int n) {
        if(n < 2) {
            return n;
        }

        int count = 0;
        for(int i = 1; i * i <= n; i++) {
            count++;
        }

        return count;
    }

    public int evenBetter(int n) {
        return (int)Math.sqrt(n);
    }
}
