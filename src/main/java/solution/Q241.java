package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Different Ways to Add Parentheses",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/different-ways-to-add-parentheses/"
)
public class Q241 {
    /*
    * mid point dp
    * */
    public List<Integer> diffWaysToCompute(String expression) {
        int m = expression.length();
        List<String> e = new ArrayList<>();

        for(int l = 0, r = 0; r < m; r++) {
            char c = expression.charAt(r);
            if(!Character.isDigit(c)) {
                e.add(expression.substring(l, r));
                e.add(new String(c + ""));
                l = r + 1;
            } else if(r == m - 1) {
                e.add(expression.substring(l, m));
            }
        }

        int n = e.size();
        List<Integer>[][] dp = new List[n][n];
        for(int i = n - 1; i >= 0; i -= 2) {
            for(int j = i; j < n; j += 2) {
                dp[i][j] = new ArrayList<>();
                if(i == j) {
                    dp[i][j].add(Integer.valueOf(e.get(i)));
                } else {
                    for(int k = i + 1; k < j; k += 2) {
                        List<Integer> l = dp[i][k - 1];
                        List<Integer> r = dp[k + 1][j];
                        String op = e.get(k);
                        for(int x : l) {
                            for(int y : r) {
                                if(op.equals("*")) {
                                    dp[i][j].add(x * y);
                                } else if(op.equals("+")) {
                                    dp[i][j].add(x + y);
                                } else {
                                    dp[i][j].add(x - y);
                                }
                            }
                        }
                    }
                }

            }
        }

        return dp[0][n - 1];
    }
}
