package solution.weekly288;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimize Result by Adding Parentheses to Expression",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/contest/weekly-contest-288/problems/minimize-result-by-adding-parentheses-to-expression/"
)
public class Q2232 {
    /**
     * Time:  O(N ^ 3)
     * Space: O(N)
     * */
    public String minimizeResult(String expression) {
        String[] exp = expression.split("\\+");
        String l = exp[0], r = exp[1];
        String ans = "";
        int min = Integer.MAX_VALUE;
        int p1 = 0;
        int p2 = 0;
        int m = l.length();
        int n = r.length();
        for(int i = 0; i < m; i++) {
            for(int j = 1; j <= n; j++) {
                int lv = Integer.parseInt(l.substring(i, m));
                int rv = Integer.parseInt(r.substring(0, j));
                int lm = i == 0 ? 1 : Integer.parseInt(l.substring(0, i));
                int rm = j == n ? 1 : Integer.parseInt(r.substring(j, n));
                int res = (lv + rv) * lm * rm;

                if(res < min) {
                    min = res;
                    p1 = i;
                    p2 = j;
                }
            }
        }

        return l.substring(0, p1) + "(" + l.substring(p1, m) + "+" + r.substring(0, p2) + ")" + r.substring(p2, n);
    }
}
