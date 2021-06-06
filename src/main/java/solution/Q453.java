package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Moves to Equal Array Elements",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/minimum-moves-to-equal-array-elements/"
)
public class Q453 {
    public int minMoves(int[] nums) {
        return mathSolution(nums);
    }

    private int sortSolution(int[] nums) {
        int cnt = 0;
        Arrays.sort(nums);

        for(int i = nums.length - 1; i > 0; i--) {
            cnt += (nums[i] - nums[0]);
        }

        return cnt;
    }

    /*
        let
            c = sum(nums),
            x = min round to equalize array
            n = len(nums)
            y = value when all elements are equal
            min = min(nums)

        c + x * (n - 1) = y * n

        y = min + x;

        -> x = c - min * n
    */
    private int mathSolution(int[] nums) {
        int n = nums.length;
        int min = Integer.MAX_VALUE;
        int sum = 0;
        for(int num : nums) {
            sum += num;
            min = Math.min(min, num);
        }

        return sum - min * n;
    }
}
