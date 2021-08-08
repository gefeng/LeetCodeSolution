package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Delete Characters to Make Fancy String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/delete-characters-to-make-fancy-string/"
)
public class Q1957 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String makeFancyString(String s) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();

        int l = 0;
        int r = 0;
        while(r < n) {
            if(s.charAt(l) != s.charAt(r)) {
                l = r;
            }

            if(r - l + 1 < 3) {
                sb.append(s.charAt(r));
            }

            r++;
        }

        return sb.toString();
    }
}
