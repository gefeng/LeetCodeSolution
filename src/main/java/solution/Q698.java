package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Partition to K Equal Sum Subsets",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/partition-to-k-equal-sum-subsets/"
)
public class Q698 {
    /*
    * 这题我试了两种dfs的方法都不能ac，因为有个test case
    * [2,2,2,2,2,2,2,2,2,2,2,2,2,3,3] 8
    * 需要更优化的dp解，不想研究了，这题难度是超过hard的
    * */
    public boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        int max = 0;
        for(int n : nums) {
            sum += n;
            max = Math.max(max, n);
        }

        int targetSum = sum / k;
        if(sum % k != 0 || max > targetSum)
            return false;

        return recursive(nums, nums.length - 1, targetSum, new int[k]);
        //return canSplit(nums, 0, k, targetSum, 0, new boolean[nums.length]);
    }

    private boolean canSplit(int[] nums, int start, int k, int targetSum, int currSum, boolean[] seen) {
        if(k == 0) {
            return true;
        }

        if(currSum == targetSum) {
            return canSplit(nums, 0, k - 1, targetSum, 0, seen);
        }

        for(int i = start; i < nums.length; i++) {
            if(seen[i] || nums[i] + currSum > targetSum) {
                continue;
            }

            seen[i] = true;
            if(canSplit(nums, i + 1, k, targetSum, currSum + nums[i], seen)) {
                return true;
            }
            seen[i] = false;
        }

        return false;
    }
    private boolean recursive(int[] nums, int idx, int targetSum, int[] subsets) {
        if(idx < 0)
            return true;

        int x = nums[idx];
        for(int i = 0; i < subsets.length; i++) {
            if(x + subsets[i] <= targetSum) {
                subsets[i] += x;
                if(recursive(nums, idx - 1, targetSum, subsets)) {
                    return true;
                }
                subsets[i] -= x;
            }
        }

        return false;
    }
}
