package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check If a String Can Break Another String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/check-if-a-string-can-break-another-string/"
)
public class Q1433 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean checkIfCanBreak(String s1, String s2) {
        return canBreak(s1, s2) || canBreak(s2, s1);
    }

    private boolean canBreak(String s1, String s2) {
        int n = s1.length();
        int[] cnt = new int[26];

        for(int i = 0; i < n; i++) {
            cnt[s1.charAt(i) - 'a']++;
        }

        for(int i = 0; i < n; i++) {
            char c = s2.charAt(i);

            int ceiling = -1;
            for(int j = 0; j < 26; j++) {
                if(j >= c - 'a' && cnt[j] != 0) {
                    ceiling = j;
                    cnt[j]--;
                    break;
                }
            }

            if(ceiling == -1) {
                return false;
            }
        }

        return true;
    }
}
