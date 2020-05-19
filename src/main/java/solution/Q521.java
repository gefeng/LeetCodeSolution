package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Uncommon Subsequence I",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q521 {
    public int findLUSlength(String a, String b) {
        if(a.equals(b))
            return -1;

        return Math.max(a.length(), b.length());
    }
}
