package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Get Equal Substrings Within Budget",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BINARY_SEARCH,
        url = "https://leetcode.com/problems/get-equal-substrings-within-budget/"
)
public class Q1208 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int equalSubstring(String s, String t, int maxCost) {
        int n = s.length();
        int cost = 0;
        int ans = 0;

        for(int l = 0, r = 0; r < n; r++) {
            cost += Math.abs(s.charAt(r) - t.charAt(r));

            while(cost > maxCost) {
                cost -= Math.abs(s.charAt(l) - t.charAt(l++));
            }

            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }
}
