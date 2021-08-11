package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Maximum Number of Coins You Can Get",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/maximum-number-of-coins-you-can-get/"
)
public class Q1561 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int maxCoins(int[] piles) {
        int n = piles.length;
        int res = 0;

        Arrays.sort(piles);

        for(int i = n - 2, a = n - 1, b = 0; b < i; i -= 2, a -= 2, b++) {
            res += piles[i];
        }

        return res;
    }
}
