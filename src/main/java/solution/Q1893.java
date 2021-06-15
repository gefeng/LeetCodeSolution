package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if All the Integers in a Range Are Covered",
        difficulty = QDifficulty.EASY,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/check-if-all-the-integers-in-a-range-are-covered/"
)
public class Q1893 {
    public boolean isCovered(int[][] ranges, int left, int right) {
        for(int i = left; i <= right; i++) {
            if(!cover(i, ranges)) {
                return false;
            }
        }
        return true;
    }

    private boolean cover(int x, int[][] ranges) {
        for(int[] r : ranges) {
            if(x >= r[0] && x <= r[1]) {
                return true;
            }
        }

        return false;
    }
}
