package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Clumsy Factorial",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/clumsy-factorial/"
)
public class Q1006 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int clumsy(int n) {
        int ans = 0;
        int sign = 1;
        while(n > 0) {
            int res = n * sign;
            n--;
            if(n > 0) {
                res *= n;
            }
            n--;
            if(n > 0) {
                res /= n;
            }
            n--;
            if(n > 0) {
                res += n;
            }
            n--;
            ans += res;
            sign = -1;
        }

        return ans;
    }
}
