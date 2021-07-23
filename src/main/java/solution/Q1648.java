package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Sell Diminishing-Valued Colored Balls",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/sell-diminishing-valued-colored-balls/"
)
public class Q1648 {
    /**
     * Time:  O(NlogN)
     * Space: O(logN)
     * */
    private static final int MOD = (int)1e9 + 7;
    public int maxProfit(int[] inventory, int orders) {
        int n = inventory.length;
        long ret = 0;

        Arrays.sort(inventory);

        for(int i = n - 1; i >= 0; i--) {
            long best = i != 0 ? inventory[i] - inventory[i - 1] : inventory[i];
            long need = Math.min(orders, (long)best * (n - i));

            long r = inventory[i];
            long l = r - need / (n - i);

            ret = ret + ((r + 1) * r / 2 - (l + 1) * l / 2) * (n - i) % MOD;
            ret = ret + l * (need % (n - i)) % MOD;
            ret = ret % MOD;

            orders -= need;

            if(orders == 0) {
                break;
            }
        }

        return (int)ret;
    }
}
