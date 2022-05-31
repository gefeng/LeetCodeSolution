package solution.weekly295;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Rearrange Characters to Make Target String",
        difficulty = QDifficulty.EASY,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/weekly-contest-295/problems/rearrange-characters-to-make-target-string/"
)
public class Q2287 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int rearrangeCharacters(String s, String target) {
        int ans = Integer.MAX_VALUE;
        int n = s.length();
        int m = target.length();
        int[] cnt1 = new int[26];
        int[] cnt2 = new int[26];
        for(int i = 0; i < n; i++) {
            cnt1[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < m; i++) {
            cnt2[target.charAt(i) - 'a']++;
        }

        for(int i = 0; i < 26; i++) {
            if(cnt2[i] != 0) {
                ans = Math.min(ans, cnt1[i] / cnt2[i]);
            }
        }

        return ans;
    }
}
