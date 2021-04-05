package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Problem(
        title = "Concatenated Words",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/concatenated-words/"
)
public class Q472 {
    public List<String> findAllConcatenatedWordsInADict(String[] words) {
        List<String> ans = new ArrayList<>();
        if(words.length == 1)
            return ans;

        HashSet<String> dict = new HashSet<>();
        for(String word : words) {
            if(!word.isEmpty())
                dict.add(word);
        }

        for(String word : words) {
            if(dfsFindMemo(word, 0, dict, new Boolean[word.length()]))
                ans.add(word);
        }

        return ans;
    }

    private boolean dfsFindMemo(String word, int start, HashSet<String> dict, Boolean[] memo) {
        if(word.isEmpty())
            return false;
        if(start == word.length())
            return true;

        if(memo[start] != null)
            return memo[start];

        boolean res = false;
        for(int i = start; i < word.length(); i++) {
            if(start == 0 && i == word.length() - 1)
                res = false;
            else if(dict.contains(word.substring(start, i + 1))) {
                res = dfsFindMemo(word, i + 1, dict, memo);
                if(res)
                    break;
            }
        }

        memo[start] = res;
        return res;
    }

    /*
    * dp[i] cut at i is or is not valid
    * start with empty string, keep expanding problem size by one
    * */
    private boolean dpFind(String word, HashSet<String> dict) {
        if(word.isEmpty())
            return false;

        boolean[] dp = new boolean[word.length() + 1];
        dp[0] = true;

        for(int i = 1; i < dp.length; i++) {
            for(int j = 0; j < i; j++) {
                if(dp[j] && dict.contains(word.substring(j, i))) {
                    if(j != 0 && i != word.length()) {
                        dp[i] = true;
                        break;
                    }
                }
            }
        }

        return dp[word.length()];
    }
}
