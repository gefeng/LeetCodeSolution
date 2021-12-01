package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem (
        title = "Long Pressed Name",
        difficulty = QDifficulty.EASY,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/long-pressed-name/"
)
public class Q925 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean isLongPressedName(String name, String typed) {
        int m = name.length();
        int n = typed.length();

        int i = 0;
        int j = 0;
        for(; i < m || j < n;) {
            if(i < m && j < n && name.charAt(i) == typed.charAt(j)) {
                i++;
                j++;
            } else if(i > 0 && j < n && name.charAt(i - 1) == typed.charAt(j)) {
                while(j < n && name.charAt(i - 1) == typed.charAt(j)) {
                    j++;
                }
            } else {
                return false;
            }
        }

        return i == m && j == n;
    }
}
