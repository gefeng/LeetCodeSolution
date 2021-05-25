package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Longest String Chain",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/longest-string-chain/"
)
public class Q1048 {
    public int longestStrChain(String[] words) {
        return dpSolution1(words);
    }

    /*
        解法1： 比较常规的找LIS, O(N^2*L) L是word的平均长度
        state:
            dp[i] means the longest string chain ends with words[i]
        transition:
            dp[i] = max(dp[j]) + 1 j -> [0, i - 1] where words[j] is a predecessor of words[i]
    */
    private int dpSolution1(String[] words) {
        int n = words.length;
        int[] dp = new int[n];

        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));

        int lsc = 0;
        for(int i = 0; i < n; i++) {
            dp[i] = 1;
            for(int j = 0; j < i; j++) {
                if(isPredecessor(words[j], words[i])) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            lsc = Math.max(lsc, dp[i]);
        }

        return lsc;
    }

    private boolean isPredecessor(String s1, String s2) {
        if(s2.length() - s1.length() != 1) {
            return false;
        }

        int skip = 0;
        int i = 0, j = 0;
        while(i < s1.length() && j < s2.length()) {
            if(s1.charAt(i) != s2.charAt(j)) {
                if(skip == 1) {
                    return false;
                }
                skip++;
                j++;
            } else {
                i++;
                j++;
            }
        }

        return true;
    }

    /*
    * 解法2：比较巧妙的把问题转换成loop当前word的每一个位置，看删掉这个位置上的字母后的单词是不是在之前出现过，
    * 如果出现过就取以之前单词为结尾的最长子串的length + 1。如果没有就为默认1
    * 这样的好处是把原本 O(N^2 * L) 的复杂度转成 O(N * L^2) 由于题目中设定L远小于N, 所以这种解法更快.
    * 但空间复杂度上升，因为需要保存出现过的word。O(N * L)
    * */
    private int dpSolution2(String[] words) {
        int n = words.length;
        Map<String, Integer> dp = new HashMap<>();

        Arrays.sort(words, (a, b) -> Integer.compare(a.length(), b.length()));

        int lsc = 0;
        for(int i = 0; i < n; i++) {
            String word = words[i];
            int m = word.length();
            int maxLen = 0;
            for(int j = 0; j < m; j++) {
                String skipj = word.substring(0, j) + word.substring(j + 1, m);
                maxLen = Math.max(maxLen, dp.getOrDefault(skipj, 0) + 1);
            }

            dp.put(word, maxLen);
            lsc = Math.max(lsc, maxLen);
        }

        return lsc;
    }
}
