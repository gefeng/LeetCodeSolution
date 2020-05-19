package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Length of Last Word",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q58 {
    public int lengthOfLastWord(String s) {
        return s.trim().length() - s.trim().lastIndexOf(' ') - 1;
    }
}
