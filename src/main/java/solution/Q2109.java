package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Adding Spaces to a String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/adding-spaces-to-a-string/"
)
public class Q2109 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String addSpaces(String s, int[] spaces) {
        StringBuilder sb = new StringBuilder();
        int m = spaces.length;
        int n = s.length();
        for(int i = 0, j = 0; i < n; i++) {
            if(j < m && i == spaces[j]) {
                sb.append(" ");
                j++;
            }
            sb.append(s.charAt(i));
        }

        return sb.toString();
    }
}
