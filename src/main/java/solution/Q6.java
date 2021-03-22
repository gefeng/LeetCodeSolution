package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "ZigZag Conversion",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/zigzag-conversion/"
)
public class Q6 {
    public String convert(String s, int numRows) {
        if(s.length() < 2 || numRows == 1)
            return s;

        StringBuilder[] rows = new StringBuilder[numRows];
        for(int i = 0; i < numRows; i++)
            rows[i] = new StringBuilder();

        boolean goDown = true;
        int r = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if(r == 0)
                goDown = true;
            else if(r == numRows - 1)
                goDown = false;

            if(goDown)
                rows[r++].append(c);
            else
                rows[r--].append(c);
        }

        StringBuilder ans = new StringBuilder();
        for(StringBuilder sb : rows)
            ans.append(sb);
        return ans.toString();
    }
}
