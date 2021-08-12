package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Insertions to Balance a Parentheses String",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-insertions-to-balance-a-parentheses-string/"
)
public class Q1541 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minInsertions(String s) {
        int n = s.length();
        int open = 0;
        int close = 0;
        int res = 0;

        for(int i = 0; i < n; i++) {
            char c = s.charAt(i);

            if(c == '(') {
                open++;

                if(close == 1) {
                    if(open == 1) {
                        res += 2;
                    } else {
                        res++;
                        open--;
                    }
                    close = 0;
                }
            } else {
                close++;

                if(close == 2) {
                    if(open == 0) {
                        res++;
                    } else {
                        open--;
                    }

                    close = 0;
                }
            }
        }

        if(open == 0) {
            return close == 1 ? res + 2 : res;
        } else {
            return res + 2 * open - close;
        }
    }
}
