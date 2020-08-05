package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "N-th Tribonacci Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.RECURSION,
        url = "https://leetcode.com/problems/n-th-tribonacci-number/"
)
public class Q1137 {
    HashMap<Integer, Integer> memo = new HashMap<>();
    public int tribonacci(int n) {
        if(n == 0)
            return 0;
        if(n == 1 || n == 2)
            return 1;

        Integer ans = memo.get(n);
        if(ans == null) {
            ans = tribonacci(n - 3) + tribonacci(n - 2) + tribonacci(n - 1);
            memo.put(n, ans);
        }

        return ans;
    }

    public int tribonacciDp(int n) {
        if(n == 0)
            return 0;
        if(n == 1 || n == 2)
            return 1;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 1;

        for(int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        return dp[n - 1];
    }
}
