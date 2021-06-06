package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Product of Word Lengths",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/maximum-product-of-word-lengths/"
)
public class Q318 {
    public int maxProduct(String[] words) {
        return bitSolution(words);
    }

    private int bitSolution(String[] words) {
        int n = words.length;
        int[] freq = new int[n];

        for(int i = 0; i < n; i++) {
            int len = words[i].length();
            for(int j = 0; j < len; j++) {
                freq[i] |= (1 << words[i].charAt(j) - 'a');
            }
        }

        int max = 0;
        for(int i = 0; i < n - 1; i++) {
            int len = words[i].length();
            for(int j = i + 1; j < n; j++) {
                if((freq[i] & freq[j]) == 0) {
                    max = Math.max(max, len * words[j].length());
                }
            }
        }

        return max;
    }

    private int freqArraySolution(String[] words) {
        int n = words.length;
        int[][] freq = new int[n][26];

        for(int i = 0; i < n; i++) {
            int len = words[i].length();
            String word = words[i];
            for(int j = 0; j < len; j++) {
                freq[i][word.charAt(j) - 'a']++;
            }
        }

        int max = 0;
        for(int i = 0; i < n - 1; i++) {
            int len1 = words[i].length();
            for(int j = i + 1; j < n; j++) {
                if(!hasCommon(freq[i], freq[j])) {
                    max = Math.max(max, len1 * words[j].length());
                }
            }
        }

        return max;
    }

    private boolean hasCommon(int[] f1, int[] f2) {
        for(int i = 0; i < f1.length; i++) {
            if(f1[i] > 0 && f2[i] > 0) {
                return true;
            }
        }

        return false;
    }
}
