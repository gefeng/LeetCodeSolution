package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Split Array With Equal Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/split-array-with-equal-sum/"
)
public class Q548 {
    /*
    * 很巧秒的把n^3的复杂度用hashset降低成n^2
    * */
    public boolean splitArray(int[] nums) {
        return nestedTwoLoopSolution(nums);
    }

    private boolean nestedThreeLoopSolution(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for(int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        for(int i = 1; i <= n - 6; i++) {
            for(int k = 5; k < n - 1; k++) {
                for(int j = i + 2; j <= k - 2; j++) {
                    if(canSplit(prefixSum, i, j, k)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean canSplit(int[] prefixSum, int i, int j, int k) {
        int sum1 = prefixSum[i];
        int sum2 = prefixSum[j] - prefixSum[i + 1];
        int sum3 = prefixSum[k] - prefixSum[j + 1];
        int sum4 = prefixSum[prefixSum.length - 1] - prefixSum[k + 1];

        return (sum1 == sum2) && (sum2 == sum3) && (sum3 == sum4);
    }

    private boolean nestedTwoLoopSolution(int[] nums) {
        int n = nums.length;
        int[] prefixSum = new int[n + 1];
        for(int i = 1; i < n + 1; i++) {
            prefixSum[i] = prefixSum[i - 1] + nums[i - 1];
        }

        for(int j = 3; j < n - 3; j++) {
            Set<Integer> set = new HashSet<>();
            for(int i = 1; i < j - 1; i++) {
                if(prefixSum[i] == prefixSum[j] - prefixSum[i+1]) {
                    set.add(prefixSum[i]);
                }
            }

            for(int k = j + 2; k < n - 1; k++) {
                int sum1 = prefixSum[k] - prefixSum[j + 1];
                int sum2 = prefixSum[n] - prefixSum[k + 1];
                if(sum1 == sum2 && set.contains(sum1)) {
                    return true;
                }
            }
        }

        return false;
    }
}
