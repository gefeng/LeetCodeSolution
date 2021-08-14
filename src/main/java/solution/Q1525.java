package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Good Ways to Split a String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/number-of-good-ways-to-split-a-string/"
)
public class Q1525 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int numSplits(String s) {
        int n = s.length();
        int res = 0;

        int[] cntL = new int[26];
        int[] cntR = new int[26];

        cntL[s.charAt(0) - 'a']++;
        for(int i = 1; i < n; i++) {
            cntR[s.charAt(i) - 'a']++;
        }

        for(int i = 1; i < n; i++) {
            if(isGood(cntL, cntR)) {
                res++;
            }

            cntL[s.charAt(i) - 'a']++;
            cntR[s.charAt(i) - 'a']--;
        }

        return res;
    }

    private boolean isGood(int[] cntL, int[] cntR) {
        int cnt = 0;
        for(int i = 0; i < 26; i++) {
            if(cntL[i] != 0) {
                cnt++;
            }
            if(cntR[i] != 0) {
                cnt--;
            }
        }
        return cnt == 0;
    }
}
