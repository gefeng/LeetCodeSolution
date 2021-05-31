package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Truncate Sentence",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/truncate-sentence/"
)
public class Q1816 {
    public String truncateSentence(String s, int k) {
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < k; i++) {
            sb.append(words[i]).append(" ");
        }

        return sb.deleteCharAt(sb.length() - 1).toString();
    }
}
