package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Good People Based on Statements",
        difficulty = QDifficulty.HARD,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/problems/maximum-good-people-based-on-statements/"
)
public class Q2151 {
    /**
     * Time:  O(2 ^ N * N ^ 2)
     * Space: O(1)
     * */
    public int maximumGood(int[][] statements) {
        int n = statements.length;
        int ans = 0;
        for(int mask = (1 << n) - 1; mask > 0; mask--) {
            if(isOk(statements, mask)) {
                ans = Math.max(ans, Integer.bitCount(mask));
            }
        }
        return ans;
    }

    private boolean isOk(int[][] s, int mask) {
        int n = s.length;
        for(int i = 0; i < n; i++) {
            if((mask & (1 << i)) != 0) {
                for(int j = 0; j < n; j++) {
                    if(i == j) continue;
                    if(s[i][j] == 0 && (mask & (1 << j)) != 0) return false;
                    if(s[i][j] == 1 && (mask & (1 << j)) == 0) return false;
                }
            }
        }
        return true;
    }
}
