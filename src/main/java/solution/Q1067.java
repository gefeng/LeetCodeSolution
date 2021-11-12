package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Digit Count in Range",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/digit-count-in-range/"
)
public class Q1067 {
    /**
     * Digit dp which is a new topic for me.
     * The general idea is to build the number within the upper bound.
     * In order to build such number the critical information we need is that is the current digit should be bounded.
     * Let's say the upper bound is 1234 and we are building 123x where x can be 0 up to 4. And if we pick 3 in this case,
     * the rest of the digits should be free to pick from [0, 9] since the number will be guaranteed less than upper bound.
     *
     * Time:  O(N ^ 2) where N is the number of digits.
     * Space: O(N ^ 2)
     * */
    public int digitsCount(int d, int low, int high) {
        return solve(d, high) - solve(d, low - 1);
    }

    private int solve(int d, int hi) {
        int x = hi;
        int cntD = 0;
        while(x != 0) {
            x /= 10;
            cntD++;
        }
        int[] digits = new int[cntD];
        x = hi;
        for(int i = cntD - 1; i >= 0; i--) {
            digits[i] = x % 10;
            x /= 10;
        }

        return dfs(d, digits, 0, 0, 1, 1, new Integer[10][10][2][2]);
    }

    private int dfs(int d, int[] digits, int cur, int cnt,
                    int hlz /*has leading zero or not*/, int hb/*has bound or not*/, Integer[][][][] memo) {
        if(cur == digits.length) {
            return cnt;
        }

        if(memo[cur][cnt][hlz][hb] != null) {
            return memo[cur][cnt][hlz][hb];
        }

        int tot = 0;
        int end = hb == 1 ? digits[cur] : 9;
        for(int i = 0; i <= end; i++) {
            int nhlz = (i == 0 && hlz == 1) ? 1 : 0;
            int nhb = (hb == 1 && i == end) ? 1 : 0;
            int ncnt = nhlz == 1 ? 0 : cnt + (i == d ? 1 : 0);
            tot += dfs(d, digits, cur + 1, ncnt, nhlz, nhb, memo);
        }

        return memo[cur][cnt][hlz][hb] = tot;
    }
}
