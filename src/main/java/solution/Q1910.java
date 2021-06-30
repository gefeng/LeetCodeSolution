package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Remove All Occurrences of a Substring",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/remove-all-occurrences-of-a-substring/"
)
public class Q1910 {
    public String removeOccurrences(String s, String part) {
        int n = part.length();
        while(true) {
            int m = s.length();
            int idx = s.indexOf(part);

            if(idx < 0) {
                break;
            }

            s = s.substring(0, idx) + s.substring(idx + n, m);
        }

        return s;
    }
}
