package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Maximum Score After N Operations",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximize-score-after-n-operations/"
)
public class Q1799 {
    /*
    * time: O(2^n * n^2)
    * space: O(2^n)
    * */
    public int maxScore(int[] nums) {
        int n = nums.length;

        // pre-compute gcd to improve time complexity
        int[][] gcd = new int[n][n];
        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                int v = gcd(nums[i], nums[j]);
                gcd[i][j] = v;
                gcd[j][i] = v;
            }
        }

        Integer[] memo = new Integer[1 << n];
        return dfs(nums, (1 << n) - 1, 1, memo, gcd);
    }

    private int dfs(int[] nums, int state, int r, Integer[] memo, int[][] gcd) {
        int n = nums.length;
        if(r > n) {
            return 0;
        }

        if(memo[state] != null) {
            return memo[state];
        }

        int score = 0;
        for(int i = 0; i < n; i++) {
            if((state & (1 << i)) == 0) {
                continue;
            }
            int nState = state ^ (1 << i);
            for(int j = 0; j < n; j++) {
                if((nState & (1 << j)) == 0) {
                    continue;
                }

                nState ^= (1 << j);
                score = Math.max(score, dfs(nums, nState, r + 1, memo, gcd) + r * gcd[i][j]);
                nState ^= (1 << j);
            }
        }

        return memo[state] = score;
    }

    private int gcd(int a, int b) {
        if(b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }
}
