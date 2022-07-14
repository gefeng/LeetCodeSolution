package solution.weekly301;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Amount of Time to Fill Cups",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-301/problems/minimum-amount-of-time-to-fill-cups/"
)
public class Q2335 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int fillCups(int[] amount) {
        Arrays.sort(amount);
        return amount[0] + amount[1] <= amount[2] ? amount[2] : (amount[0] + amount[1] - amount[2] + 1) / 2 + amount[2];
    }
}
