package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Construct K Palindrome Strings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/construct-k-palindrome-strings/"
)
public class Q1400 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean canConstruct(String s, int k) {
        int n = s.length();
        int[] cnt = new int[26];

        for(int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        int cntOdd = 0;
        for(int i = 0; i < 26; i++) {
            if(cnt[i] % 2 == 1) {
                cntOdd++;
            }
        }

        return k <= n && cntOdd <= k;
    }
}
