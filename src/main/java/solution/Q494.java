package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Target Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/target-sum/"
)
public class Q494 {

    /**brute force solution O(2^n)**/
    int count = 0;
    private void findTargetSumDFS(int[] nums, int index, int S) {
        if(index > nums.length - 1) {
            if(S == 0)
                count++;
            return;
        }

        findTargetSumDFS(nums, index + 1, S - nums[index]);
        findTargetSumDFS(nums, index + 1, S + nums[index]);
    }

    public int findTargetSumWays(int[] nums, int S) {
        findTargetSumDFS(nums, 0, S);
        return count;
    }

    /**memoized solution**/
    private int dfsMS(int[] nums, int index, int sum, int S, int[][] memo) {
        if(index == nums.length) {
            if(sum == S)
                return 1;
            else
                return 0;
        }

        int col = sum + (memo[0].length - 1) / 2;
        if(memo[index][col] != -1)
            return memo[index][col];

        int add = dfsMS(nums, index + 1, sum + nums[index], S, memo);
        int sub = dfsMS(nums, index + 1, sum - nums[index], S, memo);
        memo[index][col] = add + sub;
        return add + sub;
    }
    public int findTargetSumWaysMS(int[] nums, int S) {
        int sum = 0;
        for(int n : nums)
            sum += n;
        int[][] memo = new int[nums.length][sum * 2 + 1];
        for(int[] row : memo)
            Arrays.fill(row, -1);

        return dfsMS(nums, 0, 0, S, memo);
    }
}
