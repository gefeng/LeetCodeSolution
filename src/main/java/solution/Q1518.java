package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Water Bottles",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/water-bottles/"
)
public class Q1518 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public int numWaterBottles(int numBottles, int numExchange) {
        int res = 0;
        int full = numBottles;
        int empty = 0;
        while(full != 0) {
            res += full;

            empty += full;
            full = empty / numExchange;
            empty = empty % numExchange;
        }

        return res;
    }
}
