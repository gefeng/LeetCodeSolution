package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check If String Is a Prefix of Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/check-if-string-is-a-prefix-of-array/"
)
public class Q1961 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public boolean isPrefixString(String s, String[] words) {
        int n = s.length();
        StringBuilder sb = new StringBuilder();
        for(String w : words) {
            sb.append(w);
            if(sb.length() == n) {
                return s.equals(sb.toString());
            } else if(sb.length() > n) {
                return false;
            }
        }

        return false;
    }
}
