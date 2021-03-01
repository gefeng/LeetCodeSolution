package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Missing Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/missing-number/"
)
public class Q268 {
    public int missingNumber(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for(int i = 0; i <= n; i++)
            sum += i;

        for(int num : nums) {
            sum -= num;
        }

        return sum;
    }

    private int xorSolution(int[] nums) {
        int xor = nums.length;
        for(int i = 0; i < nums.length; i++) {
            xor ^= (i ^ nums[i]);
        }
        return xor;
    }
}
