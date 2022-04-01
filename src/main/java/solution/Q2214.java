package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Health to Beat Game",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-health-to-beat-game/"
)
public class Q2214 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public long minimumHealth(int[] damage, int armor) {
        int n = damage.length;
        int max = 0;
        long sum = 1;
        for(int x : damage) {
            sum += x;
            max = Math.max(max, x);
        }
        return sum - max + Math.max(max - armor, 0);
    }
}
