package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Number of Vowels in a Substring of Given Length",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/maximum-number-of-vowels-in-a-substring-of-given-length/"
)
public class Q1456 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int maxVowels(String s, int k) {
        int n = s.length();
        int cnt = 0;
        int ans = 0;

        boolean[] v = new boolean[26];
        v['a' - 'a'] = true;
        v['e' - 'a'] = true;
        v['i' - 'a'] = true;
        v['o' - 'a'] = true;
        v['u' - 'a'] = true;
        for(int l = 0, r = 0; r < n; r++) {
            if(v[s.charAt(r) - 'a']) {
                cnt++;
            }

            if(r - l + 1 > k) {
                if(v[s.charAt(l++) - 'a']) {
                    cnt--;
                }
            }

            ans = Math.max(ans, cnt);
        }

        return ans;
    }
}
