package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Replace the Substring for Balanced String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/replace-the-substring-for-balanced-string/"
)
public class Q1234 {
    /*
    * 思路清奇，不太好想。这题思路和一般sliding window相反，
    * 需要track window外字符个数，如果没有超过n / 4个，则shrink window
    * */
    public int balancedString(String s) {
        int n = s.length();
        int[] cnt = new int[26];

        for(int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'A']++;
        }

        if(cnt['Q' - 'A'] == n / 4 && cnt['W' - 'A'] == n / 4 &&
                cnt['E' - 'A'] == n / 4 && cnt['R' - 'A'] == n / 4) {
            return 0;
        }

        int l = 0;
        int r = 0;
        int minLen = n;
        while(r < n) {
            cnt[s.charAt(r) - 'A']--;
            while(cnt['Q' - 'A'] <= n / 4 && cnt['W' - 'A'] <= n / 4 &&
                    cnt['E' - 'A'] <= n / 4 && cnt['R' - 'A'] <= n / 4) {
                minLen = Math.min(minLen, r - l + 1);
                cnt[s.charAt(l++) - 'A']++;
            }
            r++;
        }

        return minLen;
    }
}
