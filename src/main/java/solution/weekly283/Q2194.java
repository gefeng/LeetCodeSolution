package solution.weekly283;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Cells in a Range on an Excel Sheet",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATRIX,
        url = "https://leetcode.com/contest/weekly-contest-283/problems/cells-in-a-range-on-an-excel-sheet/"
)
public class Q2194 {
    /**
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    public List<String> cellsInRange(String s) {
        List<String> ans = new ArrayList<>();

        for(char c = s.charAt(0); c <= s.charAt(3); c++) {
            for(char r = s.charAt(1); r <= s.charAt(4); r++) {
                ans.add("" + c + r);
            }
        }

        return ans;
    }
}
