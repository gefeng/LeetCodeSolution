package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Palindrome by Concatenating Two Letter Words",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/longest-palindrome-by-concatenating-two-letter-words/"
)
public class Q2131 {
    /**
     * solution from top coders without using hash table
     *  cnt[a][b] denotes number of strings "ab" so far
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public int longestPalindrome(String[] words) {
        int[][] cnt = new int[26][26];
        int ans = 0;

        for(String w : words) {
            int a = w.charAt(0) - 'a';
            int b = w.charAt(1) - 'a';
            if(cnt[b][a] > 0) {
                cnt[b][a]--;
                ans += 4;
            } else {
                cnt[a][b]++;
            }
        }

        for(int i = 0; i < 26; i++) {
            if(cnt[i][i] > 0) {
                ans += 2;
                break;
            }
        }

        return ans;
    }
}
