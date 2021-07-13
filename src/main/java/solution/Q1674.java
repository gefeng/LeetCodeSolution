package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Moves to Make Array Complementary",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.PREFIX_SUM,
        url = "https://leetcode.com/problems/minimum-moves-to-make-array-complementary/"
)
public class Q1674 {
    /*
        I think this should be hard if someone like me never saw this pattern.
        However the idea behind is pretty cool.

        Observation:
        1. make 2 moves, we can get any sum between [2, limit * 2]
        2. make 1 moves, a pair (a, b) can reach sum from [1 + min(a, b), limit + max(a, b)]
        3. make 0 moves, we get only get a + b

        22(1+min(a, b))11(a,b)11(limit+max(a,b))22
    */
    public int minMoves(int[] nums, int limit) {
        int n = nums.length;
        int[] dt = new int[2 * limit + 2];

        for(int i = 0; i < n / 2; i++) {
            int a = nums[i];
            int b = nums[n - 1 - i];
            dt[Math.min(a, b) + 1] -= 1;
            dt[a + b] -= 1;
            dt[a + b + 1] += 1;
            dt[Math.max(a, b) + limit + 1] += 1;
        }

        int ans = n;
        int curr = n;
        for(int i = 2; i <= 2 * limit; i++) {
            curr += dt[i];
            ans = Math.min(ans, curr);
        }
        return ans;
    }
}
