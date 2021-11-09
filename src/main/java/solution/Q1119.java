package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove Vowels from a String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/remove-vowels-from-a-string/"
)
public class Q1119 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String removeVowels(String s) {
        String v = "aeiou";
        StringBuilder sb = new StringBuilder();
        int n = s.length();

        for(int i = 0; i < n; i++) {
            if(v.indexOf(s.charAt(i)) < 0) {
                sb.append(s.charAt(i));
            }
        }

        return sb.toString();
    }
}
