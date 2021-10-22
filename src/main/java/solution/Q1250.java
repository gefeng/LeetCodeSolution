package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Check If It Is a Good Array",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/check-if-it-is-a-good-array/"
)
public class Q1250 {
    /**
     * Bezout's identity
     *
     * Time:  O(N * log(max(nums)))
     * Space: O(1)
     * */
    public boolean isGoodArray(int[] nums) {
        int n = nums.length;

        int x = nums[0];
        for(int i = 1; i < n; i++) {
            x = gcd(x, nums[i]);
        }

        return x == 1;
    }

    private int gcd(int a, int b) {
        if(a == 0) {
            return b;
        }

        return gcd(b % a, a);
    }
}
