package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Decode Ways",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/decode-ways/"
)
public class Q91 {
    public int numDecodings(String s) {
        return decodeMemo(s, 0, new HashMap<>());
    }

    // only need to iterate 1 or 2 steps further
    // O(n)
    private int decodeMemo(String s, int start, HashMap<Integer, Integer> memo) {
        if(start == s.length())
            return 1;

        if(memo.containsKey(start))
            return memo.get(start);

        int total = 0;
        for(int i = start; i < start + 2 && i < s.length(); i++) {
            if(isValid(s.substring(start, i + 1))) {
                total += decodeMemo(s, i + 1, memo);
            }
        }

        memo.put(start, total);
        return total;
    }

    private boolean isValid(String s) {
        if(s.length() > 2)
            return false;

        if(s.charAt(0) == '0')
            return false;

        return Integer.valueOf(s) <= 26;
    }

    private int dpSolution(String s) {
        int len = s.length();
        int[] dp = new int[len];

        if(s.charAt(0) == '0')
            return 0;

        dp[0] = 1;
        for(int i = 1; i < len; i++) {
            if(s.charAt(i) != '0')
                dp[i] += dp[i - 1];
            if(s.charAt(i - 1) != '0' && Integer.valueOf(s.substring(i - 1, i + 1)) <= 26) {
                if(i == 1)
                    dp[i]++;
                else
                    dp[i] += dp[i - 2];
            }
        }

        return dp[len - 1];
    }
}
