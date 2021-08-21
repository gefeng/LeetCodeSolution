package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Unique Substrings in Wraparound String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/unique-substrings-in-wraparound-string/"
)
public class Q467 {
    /**
     * dp[i] means # unique substring strings end with letter i + 'a'
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public int findSubstringInWraproundString(String p) {
        int n = p.length();
        int res = 0;
        int[] dp = new int[26];

        int l = 0;
        int r = 0;
        while(r < n) {
            if(r > 0 && p.charAt(r) != p.charAt(r - 1) + 1 && (p.charAt(r) != 'a' || p.charAt(r - 1) != 'z')) {
                l = r;
            }

            int i = p.charAt(r) - 'a';
            dp[i] = Math.max(dp[i], r - l + 1);

            r++;
        }

        for(int i = 0; i < 26; i++) {
            res += dp[i];
        }

        return res;
    }
}
