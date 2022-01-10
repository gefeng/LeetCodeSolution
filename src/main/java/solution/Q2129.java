package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Capitalize the Title",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/capitalize-the-title/"
)
public class Q2129 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public String capitalizeTitle(String title) {
        String[] arr = title.split(" ");
        StringBuilder sb = new StringBuilder();
        for(String s : arr) {
            int n = s.length();
            if(n <= 2) {
                sb.append(s.toLowerCase());
            } else {
                s = s.toLowerCase();
                sb.append(Character.toUpperCase(s.charAt(0)) + s.substring(1, n));
            }

            sb.append(" ");
        }

        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }
}
