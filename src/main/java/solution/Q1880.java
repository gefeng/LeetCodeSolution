package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if Word Equals Summation of Two Words",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/check-if-word-equals-summation-of-two-words/"
)
public class Q1880 {
    public boolean isSumEqual(String firstWord, String secondWord, String targetWord) {
        return toNumeric(firstWord) + toNumeric(secondWord) == toNumeric(targetWord);
    }

    private int toNumeric(String s) {
        int n = s.length();
        int num = 0;
        for(int i = 0; i < n; i++) {
            num = num * 10 + s.charAt(i) - 'a';
        }
        return num;
    }
}
