package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Odd Numbers in an Interval Range",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/count-odd-numbers-in-an-interval-range/"
)
public class Q1523 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int countOdds(int low, int high) {
        if(low % 2 == 0) {
            low++;
        }
        if(high % 2 == 0) {
            high--;
        }

        if(low > high) {
            return 0;
        }
        if(low == high) {
            return 1;
        }

        return (high - low - 1) / 2 + 2;
    }
}
