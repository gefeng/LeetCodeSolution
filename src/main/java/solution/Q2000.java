package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Reverse Prefix of Word",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/reverse-prefix-of-word/"
)
public class Q2000 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String reversePrefix(String word, char ch) {
        int idx = word.indexOf(ch);

        if(idx == -1) {
            return word;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(word.substring(0, idx + 1));
        sb.reverse();
        sb.append(word.substring(idx + 1, word.length()));

        return sb.toString();
    }
}
