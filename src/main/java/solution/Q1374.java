package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Generate a String With Characters That Have Odd Counts",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/generate-a-string-with-characters-that-have-odd-counts/"
)
public class Q1374 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public String generateTheString(int n) {
        StringBuilder sb = new StringBuilder();
        if (n % 2 == 0) {
            while (n > 1) {
                sb.append('a');
                n--;
            }
            sb.append('b');
        } else {
            while (n > 2) {
                sb.append('a');
                n--;
            }
            sb.append('b');
            n--;
            if (n > 0) {
                sb.append('c');
            }
        }

        return sb.toString();
    }
}
