package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Detect Capital",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/detect-capital/"
)
public class Q520 {
    public boolean detectCapitalUse(String word) {
        int upperCaseCount = 0;

        for(int i = 0; i < word.length(); i++) {
            if('Z' - word.charAt(i) >= 0) {
                upperCaseCount++;
            }
        }

        return upperCaseCount == 0 ||
                upperCaseCount == word.length() ||
                (upperCaseCount == 1 && 'Z' - word.charAt(0) >= 0);
    }
}
