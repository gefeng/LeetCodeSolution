package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Convert Integer to the Sum of Two No-Zero Integers",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/convert-integer-to-the-sum-of-two-no-zero-integers/"
)
public class Q1317 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int[] getNoZeroIntegers(int n) {
        int[] ans = new int[2];

        for(int i = 1; i <= n / 2; i++) {
            if(isOk(i) && isOk(n - i)) {
                ans[0] = i;
                ans[1] = n - i;
            }
        }

        return ans;
    }

    private boolean isOk(int x) {
        while(x != 0) {
            if(x % 10 == 0) {
                return false;
            }
            x /= 10;
        }

        return true;
    }
}
