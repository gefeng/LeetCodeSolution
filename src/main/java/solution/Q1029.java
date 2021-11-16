package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;

@Problem(
        title = "Two City Scheduling",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/two-city-scheduling/"
)
public class Q1029 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int twoCitySchedCost(int[][] costs) {
        int ans = 0;
        int n = costs.length;

        Arrays.sort(costs, Comparator.comparingInt(a -> Math.abs(a[0] - a[1])));

        int cntA = n / 2;
        int cntB = n / 2;
        for(int i = n - 1; i >= 0; i--) {
            if(cntA == 0) {
                ans += costs[i][1];
            } else if(cntB == 0) {
                ans += costs[i][0];
            } else {
                if(costs[i][0] < costs[i][1]) {
                    ans += costs[i][0];
                    cntA -= 1;
                } else {
                    ans += costs[i][1];
                    cntB -= 1;
                }
            }
        }

        return ans;
    }
}
