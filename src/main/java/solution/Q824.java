package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Goat",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/goat-latin"
)
public class Q824 {
    /**
     * Time:  O(M * N ^ 2)
     * Space: O(M * N)
     * */
    public String toGoatLatin(String S) {
        StringBuilder ans = new StringBuilder();
        StringBuilder suffixA = new StringBuilder();
        Set<Character> vowelSet = new HashSet<>();
        for(char c : "aeiouAEIOU".toCharArray())
            vowelSet.add(c);

        for(String word : S.split("\\s")) {
            suffixA.append('a');
            if(vowelSet.contains(word.charAt(0)))
                ans.append(' ').append(word).append("ma").append(suffixA);
            else
                ans.append(' ').append(word.substring(1)).append(word.charAt(0)).append("ma").append(suffixA);
        }

        return ans.toString().substring(1);
    }
}
