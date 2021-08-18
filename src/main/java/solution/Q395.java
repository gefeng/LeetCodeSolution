package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Substring with At Least K Repeating Characters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/longest-substring-with-at-least-k-repeating-characters/"
)
public class Q395 {
    /**
     * The solution is quite impressive.
     * I was thinking use a hashmap to save some kind of states by
     * given each character's frequency so far. But it doesn't work
     * out.
     *
     * The idea is to test each window contains 1, 2, ... 26 unique
     * characters. This is smart that sliding window is viable.
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public int longestSubstring(String s, int k) {
        int res = 0;

        for(int i = 1; i <= 26; i++) {
            res = Math.max(res, longestSubstring(s, k, i));
        }

        return res;
    }

    private int longestSubstring(String s, int k, int m) {
        int n = s.length();
        int l = 0;
        int r = 0;
        int res = 0;
        int[] cnt = new int[26];

        while(r < n) {
            cnt[s.charAt(r) - 'a']++;

            while(cntUnique(cnt) > m) {
                cnt[s.charAt(l++) - 'a']--;
            }

            if(isValidSubstring(cnt, k)) {
                res = Math.max(res, r - l + 1);
            }

            r++;
        }
        return res;
    }

    private int cntUnique(int[] cnt) {
        int res = 0;
        for(int i = 0; i < 26; i++) {
            if(cnt[i] != 0) {
                res++;
            }
        }
        return res;
    }

    private boolean isValidSubstring(int[] cnt, int k) {
        for(int i = 0; i < 26; i++) {
            if(cnt[i] > 0 && cnt[i] < k) {
                return false;
            }
        }
        return true;
    }
}
