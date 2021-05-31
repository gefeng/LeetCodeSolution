package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Number of Different Integers in a String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/number-of-different-integers-in-a-string/"
)
public class Q1805 {
    public int numDifferentIntegers(String word) {
        String[] nums = word.split("[a-z]+");
        Set<String> unique = new HashSet<>();

        for(String n : nums) {
            if(n.isEmpty()) {
                continue;
            }
            unique.add(removeLeadingZero(n));
        }

        return unique.size();
    }

    private String removeLeadingZero(String s) {
        int n = s.length();

        if(n == 1) {
            return s;
        }

        int i = 0;
        while(i < n - 1 && s.charAt(i) == '0') {
            i++;
        }

        return s.substring(i, n);
    }
}
