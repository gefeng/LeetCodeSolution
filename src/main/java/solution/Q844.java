package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Backspace String Compare",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/backspace-string-compare/"
)
public class Q844 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean backspaceCompare(String s, String t) {
        int m = s.length();
        int n = t.length();

        int i = m - 1;
        int j = n - 1;
        int cnt1 = 0;
        int cnt2 = 0;
        while(true) {
            while(i >= 0 && (s.charAt(i) == '#' || cnt1 > 0)) {
                if(s.charAt(i) == '#') cnt1++;
                else cnt1--;
                i--;
            }

            while(j >= 0 && (t.charAt(j) == '#' || cnt2 > 0)) {
                if(t.charAt(j) == '#') cnt2++;
                else cnt2--;
                j--;
            }

            if(i < 0 && j < 0) return true;
            if(i < 0 || j < 0) return false;
            if(s.charAt(i) != t.charAt(j)) return false;
            i--;
            j--;
        }
    }
}
