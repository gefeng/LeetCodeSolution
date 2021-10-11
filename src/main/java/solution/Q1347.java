package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Steps to Make Two Strings Anagram",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/minimum-number-of-steps-to-make-two-strings-anagram/"
)
public class Q1347 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minSteps(String s, String t) {
        int n = s.length();
        int ans = 0;
        int[] cnt = new int[26];

        for(int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
            cnt[t.charAt(i) - 'a']--;
        }

        for(int i = 0; i < 26; i++) {
            if(cnt[i] > 0) {
                ans += cnt[i];
            }
        }

        return ans;
    }
}
