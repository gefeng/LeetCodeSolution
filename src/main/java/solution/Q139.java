package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "Word Break",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/word-break/"
)
public class Q139 {
    public boolean wordBreak(String s, List<String> wordDict) {
        return memoSolution(s, wordDict);
    }

    private boolean memoSolution(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>();
        for(String word : wordDict)
            dict.add(word);

        return helper(s, 0, dict, new Boolean[s.length()]);
    }

    private boolean helper(String s, int start, Set<String> dict, Boolean[] memo) {
        if(start == s.length())
            return true;
        if(memo[start] != null)
            return memo[start];

        for(int i = start + 1; i <= s.length(); i++) {
            if(dict.contains(s.substring(start, i))) {
                memo[start] = helper(s, i, dict, memo);
                if(memo[start] == true)
                    return true;
            }
        }

        return false;
    }

    // O(n^3)
    private boolean dpSolution(String s, List<String> wordDict) {
        HashSet<String> dict = new HashSet<>();
        for(String word : wordDict)
            dict.add(word);

        boolean[] dp = new boolean[s.length() + 1];
        dp[0] = true;

        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j] && dict.contains(s.substring(j, i))) {
                    dp[i] = true;
                    break;
                }
            }
        }

        return dp[dp.length - 1];
    }
}
