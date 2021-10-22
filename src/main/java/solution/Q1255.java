package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Maximum Score Words Formed by Letters",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-score-words-formed-by-letters/"
)
public class Q1255 {
    /**
     * Time:  O(2 ^ N * N)
     * Space: O(N)
     * */
    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        int n = words.length;
        int ans = 0;

        int[] cnt = new int[26];
        for(char c : letters) {
            cnt[c - 'a'] += 1;
        }

        for(int i = 1; i < (1 << n); i++) {
            int[] l = new int[26];
            for(int j = 0; j < n; j++) {
                if(i << ~j < 0) {
                    for(char c : words[j].toCharArray()) {
                        l[c - 'a'] += 1;
                    }
                }
            }

            int sum = 0;
            for(int j = 0; j < 26; j++) {
                if(l[j] > cnt[j]) {
                    sum = 0;
                    break;
                }
                sum += l[j] * score[j];
            }
            ans = Math.max(ans, sum);
        }

        return  ans;
    }
}
