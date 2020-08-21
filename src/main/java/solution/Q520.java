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

    /**
     * 1st case: all uppercase [A-Z]*
     * 2nd case: all lowercase [a-z]*
     * 3rd case: initial capital [A-Z][a-z]*
     * combine 2 + 3: .[a-z]*
     * */
    public boolean detectCapitalUseRegex(String word) {
        return word.matches("[A-Z]*|.[a-z]*");
    }
}
