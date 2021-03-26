package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Regular Expression Matching",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/regular-expression-matching/"
)
public class Q10 {
    public boolean isMatch(String s, String p) {
        return recursionSolution(s, p);
    }

    private boolean recursionSolution(String s, String p) {
        if(p.isEmpty())
            return s.isEmpty();

        if(p.length() > 1 && p.charAt(1) == '*') {
            if(isMatch(s, p.substring(2)))
                return true;
            if(!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
                return isMatch(s.substring(1), p);
            }
            return false;
        } else {
            if(!s.isEmpty() && (s.charAt(0) == p.charAt(0) || p.charAt(0) == '.')) {
                return isMatch(s.substring(1), p.substring(1));
            }
            return false;
        }
    }

    private boolean dpMemoSolution(String s, String p) {
        return isMatch(s, p, 0, 0, new Boolean[s.length() + 1][p.length()]);
    }

    private boolean isMatch(String s, String p, int sIdx, int pIdx, Boolean[][] memo) {
        if(pIdx == p.length())
            return sIdx == s.length();

        if(memo[sIdx][pIdx] != null)
            return memo[sIdx][pIdx];

        boolean res = false;
        if(pIdx + 1 < p.length() && p.charAt(pIdx + 1) == '*') {
            if(isMatch(s, p, sIdx, pIdx + 2, memo))
                res = true;
            else if(sIdx < s.length() && (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '.'))
                res = isMatch(s, p, sIdx + 1, pIdx, memo);
            else
                res = false;
        } else {
            if(sIdx < s.length() && (s.charAt(sIdx) == p.charAt(pIdx) || p.charAt(pIdx) == '.')) {
                res = isMatch(s, p, sIdx + 1, pIdx + 1, memo);
            } else
                res = false;
        }

        memo[sIdx][pIdx] = res;
        return res;
    }
}
