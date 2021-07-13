package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Unique Length-3 Palindromic Subsequences",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/unique-length-3-palindromic-subsequences/"
)
public class Q1930 {
    public int countPalindromicSubsequence(String s) {
        int ans = 0;
        int n = s.length();
        int[] lMost = new int[26];
        int[] rMost = new int[26];
        Arrays.fill(lMost, -1);
        Arrays.fill(rMost, -1);

        for(int i = 0; i < n; i++) {
            if(lMost[s.charAt(i) - 'a'] == -1) {
                lMost[s.charAt(i) - 'a'] = i;
            }
        }

        for(int i = n - 1; i >= 0; i--) {
            if(rMost[s.charAt(i) - 'a'] == -1) {
                rMost[s.charAt(i) - 'a'] = i;
            }
        }

        for(int i = 0; i < 26; i++) {
            int l = lMost[i];
            int r = rMost[i];
            boolean[] seen = new boolean[26];
            for(int j = l + 1; j < r; j++) {
                seen[s.charAt(j) - 'a'] = true;
            }

            for(int j = 0; j < 26; j++) {
                ans = seen[j] ? ans + 1 : ans;
            }
        }

        return ans;
    }
}
