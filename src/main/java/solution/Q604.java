package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Design Compressed String Format",
        difficulty = QDifficulty.EASY,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-compressed-string-iterator/"
)
public class Q604 {
    private String s;
    private int idx;
    private char curChar;
    private int curCnt;
    public Q604(String compressedString) {
        s = compressedString;
        idx = 0;
        parse();
    }

    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public char next() {
        if(hasNext()) {
            if(curCnt == 0) {
                parse();
            }

            curCnt--;
            return curChar;
        }

        return ' ';
    }

    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public boolean hasNext() {
        return curCnt > 0 || idx < s.length();
    }

    private void parse() {
        int n = s.length();
        curChar = s.charAt(idx++);
        curCnt = 0;
        while(idx < n && Character.isDigit(s.charAt(idx))) {
            curCnt = curCnt * 10 + s.charAt(idx++) - '0';
        }
    }
}
