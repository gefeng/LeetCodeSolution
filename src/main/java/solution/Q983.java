package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Minimum Cost For Tickets",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimum-cost-for-tickets/"
)
public class Q983 {
    public int mincostTickets(int[] days, int[] costs) {
        return recursiveSolution(days, costs);
    }

    private int recursiveSolution(int[] days, int[] costs) {
        return dfsMinCost(days, costs, 0, new Integer[days.length]);
    }

    private int dfsMinCost(int[] days, int[] costs, int idx, Integer[] memo) {
        if(idx == days.length)
            return 0;

        if(memo[idx] != null)
            return memo[idx];

        int min = Integer.MAX_VALUE;
        min = dfsMinCost(days, costs, idx + 1, memo) + costs[0];
        min = Math.min(min, costs[1] + dfsMinCost(days, costs, nextDayIdx(days, idx, 7), memo));
        min = Math.min(min, costs[2] + dfsMinCost(days, costs, nextDayIdx(days, idx, 30), memo));

        return memo[idx] = min;
    }

    private int nextDayIdx(int[] days, int idx, int pass) {
        int nextIdx = idx;
        int nextDay = days[idx] + pass;
        while(nextIdx < days.length && days[nextIdx] < nextDay)
            nextIdx++;

        return nextIdx;
    }

    /*
        dp[i] means up to day i, the min cost of tickets
        dp[i] = min(dp[i - 1] + costs[0], dp[i - 7] + costs[1], dps[i - 30] + costs[2])
    */
    private int dpSolution(int[] days, int[] costs) {
        int n = days.length;
        int[] dp = new int[days[n - 1] + 1];
        Set<Integer> set = new HashSet<>();
        for(int day : days)
            set.add(day);

        for(int i = 1; i < dp.length; i++) {
            if(!set.contains(i))
                dp[i] = dp[i - 1];
            else {
                dp[i] = dp[i - 1] + costs[0];
                if(i - 7 >= 0)
                    dp[i] = Math.min(dp[i], dp[i - 7] + costs[1]);
                if(i - 30 >= 0)
                    dp[i] = Math.min(dp[i], dp[i - 30] + costs[2]);
            }
        }

        return dp[dp.length - 1];
    }
}
