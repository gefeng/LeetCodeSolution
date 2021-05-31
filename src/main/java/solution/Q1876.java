package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Substrings of Size Three with Distinct Characters",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/substrings-of-size-three-with-distinct-characters/"
)
public class Q1876 {
    /*
    * fixed size sliding window
    * */
    public int countGoodSubstrings(String s) {
        int n = s.length();
        int cnt = 0;
        int l = 0;
        int r = 0;

        while(r < n) {
            if(r - l + 1 == 3) {
                if(s.charAt(l) != s.charAt(l + 1) &&
                        s.charAt(l + 1) != s.charAt(r) &&
                        s.charAt(l) != s.charAt(r)) {
                    cnt++;
                }

                l++;
            }
            r++;
        }

        return cnt;
    }
}
