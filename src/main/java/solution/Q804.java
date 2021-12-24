package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Unique Morse Code Words",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/unique-morse-code-words/"
)
public class Q804 {
    /**
     * Time:  O(N * L)
     * Space: O(N * L)
     * */
    public int uniqueMorseRepresentations(String[] words) {
        String[] codeSet = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.",
                "...","-","..-","...-",".--","-..-","-.--","--.."};

        Set<String> uniqueCode = new HashSet<>();
        for(String word : words) {
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < word.length(); i++) {
                sb.append(codeSet[word.charAt(i) - 'a']);
            }
            uniqueCode.add(sb.toString());
        }

        return uniqueCode.size();
    }
}
