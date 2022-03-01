package solution.weekly282;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Steps to Make Two Strings Anagram II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/weekly-contest-282/problems/minimum-number-of-steps-to-make-two-strings-anagram-ii/"
)
public class Q2186 {
    /**
     * Time:  O(M + N)
     * Space: O(1)
     * */
    public int minSteps(String s, String t) {
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        int ans = 0;

        for(int i = 0; i < s.length(); i++) {
            cnt1[s.charAt(i) - 'a']++;
        }

        for(int i = 0; i < t.length(); i++) {
            cnt2[t.charAt(i) - 'a']++;
        }

        for(int i = 0; i < 26; i++) {
            if(cnt1[i] == cnt2[i]) continue;

            ans += Math.abs(cnt1[i] - cnt2[i]);
        }

        return ans;
    }
}
