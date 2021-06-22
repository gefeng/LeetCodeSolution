package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Number of Homogenous Substrings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/count-number-of-homogenous-substrings/"
)
public class Q1759 {
    private static final int MOD = (int)1e9 + 7;
    public int countHomogenous(String s) {
        int n = s.length();
        int l = 0;
        int r = 0;
        int cnt = 0;

        while(r < n) {
            if(s.charAt(r) != s.charAt(l)) {
                l = r;
            }

            cnt = (cnt + r - l + 1) % MOD;

            r++;
        }

        return cnt;
    }
}
