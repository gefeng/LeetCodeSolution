package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Substrings Containing All Three Characters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/number-of-substrings-containing-all-three-characters/"
)
public class Q1358 {
    public int numberOfSubstrings(String s) {
        return miniField(s);
    }

    private int slidingWindow(String s) {
        int n = s.length();
        int[] cnt = new int[3];

        int l = 0;
        int r = 0;
        int prefix = 0;
        int ans = 0;

        while(r < n) {
            cnt[s.charAt(r) - 'a']++;

            // valid window
            if(cnt[0] >= 1 && cnt[1] >= 1 && cnt[2] >= 1) {

                // shrink window to make sure it's minimum
                while(cnt[s.charAt(l) - 'a'] - 1 > 0) {
                    cnt[s.charAt(l++) - 'a']--;
                }

                // calculate new prefix
                prefix = l + 1;

                // break current window
                while(cnt[0] >= 1 && cnt[1] >= 1 && cnt[2] >= 1) {
                    cnt[s.charAt(l++) - 'a']--;
                }
            }

            ans += prefix;

            r++;
        }

        return ans;
    }

    private int miniField(String s) {
        int n = s.length();
        int[] cnt = new int[3];
        int l = 0;
        int r = 0;
        int ans = 0;
        while(r < n) {
            cnt[s.charAt(r++) - 'a']++;
            while(cnt[0] > 0 && cnt[1] > 0 && cnt[2] > 0) {
                cnt[s.charAt(l++) - 'a']--;
            }
            ans += l;
        }
        return ans;
    }
}
