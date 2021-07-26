package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Count Substrings That Differ by One Character",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/count-substrings-that-differ-by-one-character/"
)
public class Q1638 {
    public int countSubstrings(String s, String t) {
        return topDownDpSol(s, t);
    }

    /**
     * Considering the constraints, brute force is acceptable.
     * M: len(s)
     * N: len(t)
     * Time:  O(M ^ 3 + N ^ 2)
     * Space: O(N ^ 2)
     * */
    private int bruteForceSol(String s, String t) {
        int m = s.length();
        int n = t.length();
        int res = 0;

        Map<String, Integer> seen = new HashMap<>();
        for(int i = 0; i < n; i++) {
            for(int j = i; j < n; j++) {
                String ss = t.substring(i, j + 1);
                seen.put(ss, seen.getOrDefault(ss, 0) + 1);
            }
        }

        for(int i = 0; i < m; i++) {
            for(int j = i; j < m; j++) {
                char[] ca = s.substring(i, j + 1).toCharArray();
                for(int k = 0; k < ca.length; k++) {
                    for(char c = 'a'; c <= 'z'; c++) {
                        if(c != ca[k]) {
                            char temp = ca[k];
                            ca[k] = c;
                            String ss = new String(ca);
                            if(seen.containsKey(ss)) {
                                res += seen.get(ss);
                            }
                            ca[k] = temp;
                        }
                    }
                }
            }
        }

        return res;
    }

    /**
     * state:
     *  dp[i][j][0] means # substrings ends with s[i] exactly the same as substrings ends with t[j]
     *  dp[i][j][1] means # substrings ends with s[i] differ exactly one char from substrings end with t[j]
     * transition:
     *  dp[i][j][0] = dp[i - 1][j - 1][0] + 1 if s[i] == s[j]
     *              = 0 otherwise
     *  dp[i][j][1] = dp[i - 1][j - 1][0] + 1 if s[i] != s[j]
     *              = dp[i - 1][j - 1][1] if s[i] == s[j]
     *
     * Time:  O(M * N)
     * Space: O(M * N)
     * */
    private int topDownDpSol(String s, String t) {
        int res = 0;
        int m = s.length();
        int n = t.length();

        int[][][] dp = new int[m][n][2];

        for(int i = 0; i < m; i++) {
            if(s.charAt(i) == t.charAt(0)) {
                dp[i][0][0] = 1;
                dp[i][0][1] = 0;
            } else {
                dp[i][0][0] = 0;
                dp[i][0][1] = 1;
            }

            res += dp[i][0][1];
        }

        for(int i = 1; i < n; i++) {
            if(s.charAt(0) == t.charAt(i)) {
                dp[0][i][0] = 1;
                dp[0][i][1] = 0;
            } else {
                dp[0][i][0] = 0;
                dp[0][i][1] = 1;
            }

            res += dp[0][i][1];
        }

        for(int i = 1; i < m; i++) {
            for(int j = 1; j < n; j++) {
                if(s.charAt(i) == t.charAt(j)) {
                    dp[i][j][0] = dp[i - 1][j - 1][0] + 1;
                    dp[i][j][1] = dp[i - 1][j - 1][1];
                } else {
                    dp[i][j][0] = 0;
                    dp[i][j][1] = dp[i - 1][j - 1][0] + 1;
                }

                res += dp[i][j][1];
            }
        }

        return res;
    }
}
