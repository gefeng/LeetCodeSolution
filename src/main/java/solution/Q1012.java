package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Numbers With Repeated Digits",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/numbers-with-repeated-digits/"
)
public class Q1012 {
    /**
     * Digit DP
     *
     * Time:  O(1)
     * Space: O(1)
     * */
    public int numDupDigitsAtMostN(int n) {
        int tot = 0;
        int x = n;
        while(x != 0) {
            x /= 10;
            tot++;
        }

        int[] digits = new int[tot];
        x = n;
        for(int i = tot - 1; i >= 0; i--) {
            digits[i] = x % 10;
            x /= 10;
        }

        return dfs(digits, 0, 0, 0, 1, 1, new Integer[10][1 << 10][2][2][2]);
    }

    private int dfs(int[] digits, int cur, int mask, int hr, int hb, int hlz, Integer[][][][][] memo) {
        int n = digits.length;
        if(cur == n) {
            if(hlz == 0) {
                return hr;
            }
            return 0;
        }

        if(memo[cur][mask][hr][hb][hlz] != null) {
            return memo[cur][mask][hr][hb][hlz];
        }

        int ed = hb == 1 ? digits[cur] : 9;
        //System.out.println(cur + " " + hb + " " + ed);
        int cnt = 0;
        for(int i = 0; i <= ed; i++) {
            int nhlz = (hlz == 1 && i == 0) ? 1 : 0;
            int nhb = (hb == 1 && i == ed) ? 1 : 0;
            int nhr = (hr == 1 || (mask & (1 << i)) != 0) ? 1 : 0;
            int nMask = (hlz == 1 && i == 0) ? 0 : mask | (1 << i);
            cnt += dfs(digits, cur + 1, nMask, nhr, nhb, nhlz, memo);
        }

        return memo[cur][mask][hr][hb][hlz] = cnt;
    }
}
