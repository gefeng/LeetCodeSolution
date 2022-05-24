package solution.weekly293;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Largest Combination With Bitwise AND Greater Than Zero",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BIT_MANIPULATION,
        url = "https://leetcode.com/contest/weekly-contest-293/problems/largest-combination-with-bitwise-and-greater-than-zero/"
)
public class Q2275 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int largestCombination(int[] candidates) {
        int ans = 0;
        int[] cnt = new int[32];

        for(int x : candidates) {
            for(int i = 0; i < 30; i++) {
                if((x & (1 << i)) != 0) {
                    cnt[i]++;
                }
            }
        }

        for(int i = 0; i < 30; i++) {
            ans = Math.max(ans, cnt[i]);
        }

        return ans;
    }
}
