package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Valid Word Abbreviation",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/valid-word-abbreviation/"
)
public class Q408 {
    public boolean validWordAbbreviation(String word, String abbr) {
        int currLen = 0;
        int i = 0;
        int j = 0;
        for(; i < abbr.length(); i++) {
            char c = abbr.charAt(i);
            if(Character.isDigit(c)) {
                if(c == '0' && currLen == 0) {
                    return false;
                }
                currLen = currLen * 10 + c - '0';
            } else {
                j += currLen;
                if(j >= word.length() || word.charAt(j) != c) {
                    return false;
                }

                j++;
                currLen = 0;
            }
        }

        return j + currLen == word.length();
    }
}
