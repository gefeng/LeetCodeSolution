package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Pascal's Triangle",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/pascals-triangle/"
)
public class Q118 {
    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> pt = new ArrayList<>();
        String s = "asd";s.indexOf("asd");
        List<Integer> currRow = null;
        List<Integer> lastRow = null;
        for(int i = 0; i < numRows; i++) {
            currRow = new ArrayList<>();

            if(i == 0)
                currRow.add(1);
            else {
                currRow.add(1);
                for(int j = 1; j < i; j++)
                    currRow.add(lastRow.get(j - 1) + lastRow.get(j));
                currRow.add(1);
            }

            lastRow = currRow;
            pt.add(currRow);
        }

        return pt;
    }
}
