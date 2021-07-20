package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check If Two String Arrays are Equivalent",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/check-if-two-string-arrays-are-equivalent/"
)
public class Q1662 {
    /**
     * M: number of characters in word1
     * N: number of characters in word2
     * Time:  O(M + N)
     * Space: O(M + N)
     * */
    public boolean arrayStringsAreEqual(String[] word1, String[] word2) {
        StringBuilder sb = new StringBuilder();
        String s1;
        String s2;

        for(String w : word1) {
            sb.append(w);
        }
        s1 = sb.toString();

        sb = new StringBuilder();
        for(String w : word2) {
            sb.append(w);
        }
        s2 = sb.toString();

        return s1.equals(s2);
    }
}
