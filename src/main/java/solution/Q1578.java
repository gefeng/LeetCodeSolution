package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Deletion Cost to Avoid Repeating Letters",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STRING,
        url = "https://leetcode.com/problems/minimum-deletion-cost-to-avoid-repeating-letters/"
)
public class Q1578 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int minCost(String s, int[] cost) {
        int n = s.length();
        int res = 0;
        int l = 0;
        int r = 0;
        int sum = 0;
        int max = 0;
        while(r < n) {
            if(s.charAt(r) != s.charAt(l)) {
                res += sum - max;
                l = r;
                sum = cost[r];
                max = cost[r];
            } else {
                sum += cost[r];
                max = Math.max(max, cost[r]);
            }
            r++;
        }

        return res += sum - max;
    }
}
