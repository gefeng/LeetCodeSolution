package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Number of Squareful Arrays",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/number-of-squareful-arrays/"
)
public class Q996 {
    /**
     * state:
     *  dp[i][j] number of valid permutations with used indices representing by mask i and last number nums[j]
     *
     * we can remove duplicated permutations by counting the frequency of each elements and divides the answer by it's factorial.
     *
     * Time:  O(2 ^ N * N ^ 2)
     * Space: O(2 ^ N * N)
     * */
    public int numSquarefulPerms(int[] nums) {
        int ans = 0;
        int n = nums.length;
        if(n == 1) return 0;

        int[][] dp = new int[1 << n][n];

        for(int i = 0; i < n; i++) {
            dp[1 << i][i] = 1;
        }

        for(int i = 1; i < (1 << n); i++) {
            for(int j = 0; j < n; j++) {
                if((i & (1 << j)) == 0) {
                    continue;
                }

                for(int k = 0; k < n; k++) {
                    if(k != j && (i & (1 << k)) != 0) {
                        int x = (int)Math.sqrt(nums[k] + nums[j]);
                        if(x * x == nums[k] + nums[j]) {
                            dp[i][j] += dp[i ^ (1 << j)][k];
                        }
                    }
                }
            }
        }

        for(int i = 0; i < n; i++) {
            ans += dp[(1 << n) - 1][i];
        }

        Arrays.sort(nums);

        // remove duplicates total_permutations / factorial(repeat_time_of_xi)
        for(int i = 0; i < n;) {
            int j = i;
            while(j < n && nums[j] == nums[i]) j++;

            int fact = 1;
            for(int k = 1; k <= j - i; k++) {
                fact *= k;
            }

            ans /= fact;
            i = j;
        }

        return ans;
    }
}
