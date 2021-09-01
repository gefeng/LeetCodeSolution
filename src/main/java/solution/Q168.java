package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Excel Sheet Column Title",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/excel-sheet-column-title/"
)
public class Q168 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public String convertToTitle(int columnNumber) {
        StringBuilder sb = new StringBuilder();

        while(columnNumber != 0) {
            sb.append((char)(((columnNumber % 26) + 25) % 26 + 'A'));
            columnNumber = (columnNumber - 1) / 26;
        }

        return sb.reverse().toString();
    }
}
