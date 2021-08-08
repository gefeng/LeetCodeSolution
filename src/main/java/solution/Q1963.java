package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Swaps to Make the String Balanced",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-number-of-swaps-to-make-the-string-balanced/"
)
public class Q1963 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minSwaps(String s) {
        int n = s.length();
        int open = 0;

        for(int i = 0; i < n; i++) {
            if(s.charAt(i) == '[') {
                open++;
            } else {
                open = Math.max(0, open - 1);
            }
        }

        return (open + 1) / 2;
    }
}
