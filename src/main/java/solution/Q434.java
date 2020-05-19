package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Segments in a String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING
)
public class Q434 {
    public int countSegments(String s) {
        int count = 0;
        for(int i = 0; i < s.length(); ++i) {
            if((i == 0 || s.charAt(i - 1) == ' ') && s.charAt(i) != ' ') {
                count++;
            }
        }
        return count;
    }
}
