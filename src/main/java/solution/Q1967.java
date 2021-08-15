package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "NUmber of Strings That Appear as Substrings in Word",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/number-of-strings-that-appear-as-substrings-in-word/"
)
public class Q1967 {
    /**
     * Time:  O(N * L)
     * Space: O(1)
     * */
    public int numOfStrings(String[] patterns, String word) {
        int res = 0;

        for(String p : patterns) {
            if(word.indexOf(p) >= 0) {
                res++;
            }
        }

        return res;
    }
}
