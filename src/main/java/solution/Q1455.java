package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check If a Word Occurs As a Prefix of Any Word in a Sentence",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/check-if-a-word-occurs-as-a-prefix-of-any-word-in-a-sentence/"
)
public class Q1455 {
    /**
     * Time:  O(N + M * L)
     * Space: O(N)
     * */
    public int isPrefixOfWord(String sentence, String searchWord) {
        String[] sArr = sentence.split(" ");

        for(int i = 0; i < sArr.length; i++) {
            if(sArr[i].startsWith(searchWord)) {
                return i + 1;
            }
        }

        return -1;
    }
}
