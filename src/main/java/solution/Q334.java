package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Increasing Triplet Subsequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.NONE,
        url = "https://leetcode.com/problems/increasing-triplet-subsequence/"
)
public class Q334 {
    public boolean increasingTriplet(int[] nums) {
        return optimizedConstantSpace(nums);
    }

    // O(n^2) + O(n)
    private boolean genericDpSolution(int[] nums) {
        int dp[] = new int[nums.length];
        Arrays.fill(dp, 1);

        for(int i = 0; i < nums.length; i++) {
            for(int j = 0; j < i; j++) {
                if(nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }

            if(dp[i] > 2) {
                return true;
            }
        }

        return false;
    }

    // O(n) + O(n)
    private boolean optimizedMinMaxSolution(int[] nums) {
        int n = nums.length;
        int[] min = new int[n];
        int[] max = new int[n];
        min[0] = nums[0];
        max[n - 1] = nums[n - 1];
        for(int i = 1; i < n; i++) {
            min[i] = nums[i] < min[i - 1] ? nums[i] : min[i - 1];
        }

        for(int i = n - 2; i >= 0; i--) {
            max[i] = nums[i] > max[i + 1] ? nums[i] : max[i + 1];
        }

        for(int i = 1; i < n - 1; i++) {
            if(min[i] != nums[i] && max[i] != nums[i]) {
                return true;
            }
        }

        return false;
    }

    // O(n) + O(1)
    private boolean optimizedConstantSpace(int[] nums) {
        int numi = Integer.MAX_VALUE;
        int numj = Integer.MAX_VALUE;

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] <= numi) {
                numi = nums[i];
            } else if(nums[i] <= numj) {
                numj = nums[i];
            } else {
                return true;
            }
        }

        return false;
    }
}
