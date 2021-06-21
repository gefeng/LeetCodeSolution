package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Beauty of All Substrings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/sum-of-beauty-of-all-substrings/"
)
public class Q1781 {
    public int beautySum(String s) {
        int n = s.length();
        int sum = 0;

        for(int i = 0; i < n; i++) {
            int[] freq = new int[26];
            for(int j = i; j < n; j++) {
                freq[s.charAt(j) - 'a']++;
                sum += getBeauty(freq);
            }
        }

        return sum;
    }

    private int getBeauty(int[] freq) {
        int max = Integer.MIN_VALUE;
        int min = Integer.MAX_VALUE;
        for(int i = 0; i < freq.length; i++) {
            if(freq[i] == 0) {
                continue;
            }
            max = Math.max(max, freq[i]);
            min = Math.min(min, freq[i]);
        }

        return max - min;
    }
}
