package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Longest Repeating Character Replacement",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/longest-repeating-character-replacement/"
)
public class Q424 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int characterReplacement(String s, int k) {
        return kadaneSimilarSol(s, k);
    }

    private int validateWindowForEachLettersSol(String s, int k) {
        int res = 0;

        for(char c = 'A'; c <= 'Z'; c++) {
            res = Math.max(res, getLongest(s, k, c));
        }

        return res;
    }

    private int getLongest(String s, int k, char c) {
        int n = s.length();
        int res = 0;
        int cnt = 0;
        int l = 0;
        int r = 0;

        while(r < n) {
            if(s.charAt(r) != c) {
                while(cnt == k) {
                    if(s.charAt(l++) != c) {
                        cnt--;
                    }
                }
                cnt++;
            }

            res = Math.max(res, r - l + 1);
            r++;
        }

        return res;
    }

    private int kadaneSimilarSol(String s, int k) {
        int n = s.length();
        int l = 0;
        int r = 0;
        int max = 0;
        int[] cnt = new int[26];

        for(; r < n; r++) {
            max = Math.max(max, ++cnt[s.charAt(r) - 'A']);

            if(r - l + 1 > max + k) {
                cnt[s.charAt(l++) - 'A']--;
            }
        }

        return n - l;
    }
}
