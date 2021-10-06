package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Increasing Decreasing String",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/increasing-decreasing-string/"
)
public class Q1370 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public String sortString(String s) {
        int n = s.length();
        int[] cnt = new int[26];
        StringBuilder sb = new StringBuilder();

        for(int i = 0; i < n; i++) {
            cnt[s.charAt(i) - 'a']++;
        }

        while(sb.length() != n) {
            for(int i = 0; i < 26; i++) {
                if(cnt[i] > 0) {
                    sb.append((char)(i + 'a'));
                    cnt[i]--;
                }
            }

            for(int i = 25; i >= 0; i--) {
                if(cnt[i] > 0) {
                    sb.append((char)(i + 'a'));
                    cnt[i]--;
                }
            }
        }

        return sb.toString();
    }
}
