package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Form Largest Integer With Digits That Add up to Target",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/form-largest-integer-with-digits-that-add-up-to-target/"
)
public class Q1449 {
    /**
     * Variant unbounded knapsack
     * */
    public String largestNumber(int[] cost, int target) {
        return bottomUpSol(cost, target);
    }

    /**
     * state:
     *  dp[i] max number of digits can get with balance i
     * transition:
     *  dp[i] = Math.max(dp[i], dp[i - cost[d]]) for each digit from [1:9]
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    private String bottomUpSol(int[] cost, int target) {
        int[] dp = new int[target + 1];

        for(int i = 1; i <= target; i++) {
            dp[i] = -1;
            for(int j = 1; j <= 9; j++) {
                if(i - cost[j - 1] >= 0 && dp[i - cost[j - 1]] >= 0) {
                    dp[i] = Math.max(dp[i], dp[i - cost[j - 1]] + 1);
                }
            }
        }

        if(dp[target] <= 0) {
            return "0";
        }

        StringBuilder sb = new StringBuilder();
        for(int i = 9; i > 0; i--) {
            while(target - cost[i - 1] >= 0 && dp[target] - dp[target - cost[i - 1]] == 1) {
                target -= cost[i - 1];
                sb.append(i);
            }
        }

        return sb.toString();
    }

    private String topDownSol(int[] cost, int target) {
        return dfs(cost, target, new String[target + 1]);
    }

    private String dfs(int[] cost, int target, String[] memo) {
        if(target == 0) {
            return "";
        }
        if(target < 0) {
            return "0";
        }

        if(memo[target] != null) {
            return memo[target];
        }

        String best = "0";
        for(int i = 9; i > 0; i--) {
            String ret = dfs(cost, target - cost[i - 1], memo);

            if(ret.equals("0")) {
                continue;
            }

            ret = i + ret;
            if(best.equals("0") || ret.length() > best.length()) {
                best = ret;
            }
        }

        return memo[target] = best;
    }
}
