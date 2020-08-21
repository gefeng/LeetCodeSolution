package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Excel Sheet Column Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/excel-sheet-column-number/"
)
public class Q171 {
    public int titleToNumber(String s) {
        int colNum = 0;
        for(int i = 0; i < s.length(); i++) {
            colNum *= 26;
            colNum += (s.charAt(i) - 'A' + 1);
        }
        return colNum;
    }
}
