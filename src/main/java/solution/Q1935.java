package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Maximum Number of Words You Can Type",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/maximum-number-of-words-you-can-type/"
)
public class Q1935 {
    public int canBeTypedWords(String text, String brokenLetters) {
        Set<Character> set = new HashSet<>();
        String[] words = text.split("\\s+");

        for(int i = 0; i < brokenLetters.length(); i++) {
            set.add(brokenLetters.charAt(i));
        }

        int cnt = 0;
        for(String word : words) {
            boolean canType = true;
            for(int i = 0; i < word.length(); i++) {
                if(set.contains(word.charAt(i))) {
                    canType = false;
                    break;
                }
            }
            if(canType) {
                cnt++;
            }
        }

        return cnt;
    }
}
