package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Substrings with Only One Distinct Letter",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/count-substrings-with-only-one-distinct-letter/"
)
public class Q1180 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int countLetters(String s) {
        int n = s.length();
        int cnt = 1;
        int ans = 1;
        for(int l = 0, r = 1; r < n; r++) {
            if(s.charAt(r) != s.charAt(r - 1)) {
                cnt = 1;
            } else {
                cnt += 1;
            }

            ans += cnt;
        }

        return ans;
    }
}
