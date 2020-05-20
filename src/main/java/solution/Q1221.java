package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Split a String in Balanced Strings",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/split-a-string-in-balanced-strings/"
)
public class Q1221 {
    /*这题注意可以用一个count加减1代替两个，代码更简洁*/
    public int balancedStringSplit(String s) {
        int count = 0;
        int amount = 0;
        for(int i = 0; i < s.length(); i++) {
            count += s.charAt(i) == 'L' ? 1 : -1;
            if (count == 0) amount++;
        }
        return amount;
    }
}
