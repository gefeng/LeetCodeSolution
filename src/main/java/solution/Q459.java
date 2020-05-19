package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Repeated Substring Pattern",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/repeated-substring-pattern/"
)
public class Q459 {
    public boolean repeatedSubstringPattern(String s) {
        String repeat = s + s;
        return repeat.substring(1, repeat.length() - 1).contains(s);
    }
}
