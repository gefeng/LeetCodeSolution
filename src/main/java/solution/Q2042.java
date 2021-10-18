package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check if Numbers Are Ascending in a Sentence",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/check-if-numbers-are-ascending-in-a-sentence/"
)
public class Q2042 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean areNumbersAscending(String s) {
        String[] arr = s.split(" ");

        int pre = -1;
        for(String t : arr) {
            if(Character.isDigit(t.charAt(0))) {
                int cur = Integer.parseInt(t);

                if(pre != -1 && cur <= pre) {
                    return false;
                }

                pre = cur;
            }
        }

        return true;
    }
}
