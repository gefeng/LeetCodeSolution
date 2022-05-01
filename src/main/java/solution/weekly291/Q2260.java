package solution.weekly291;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Minimum Consecutive Cards to Pick Up",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/weekly-contest-291/problems/minimum-consecutive-cards-to-pick-up/"
)
public class Q2260 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minimumCardPickup(int[] cards) {
        int n = cards.length;
        int ans = -1;

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int c = cards[i];
            if(map.containsKey(c)) {
                ans = ans == -1 ? i - map.get(c) + 1 : Math.min(ans, i - map.get(c) + 1);
            }

            map.put(c, i);
        }

        return ans;
    }
}
