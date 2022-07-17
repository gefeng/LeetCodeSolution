package solution.weekly302;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Deletions to Make Array Divisible",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-302/problems/minimum-deletions-to-make-array-divisible/"
)
public class Q2344 {
    /**
     * Time:  O(N * log(max(numsDivide)))
     * Space: O(logN)
     * */
    public int minOperations(int[] nums, int[] numsDivide) {
        Arrays.sort(nums);

        int gcd = numsDivide[0];
        for(int x : numsDivide) {
            gcd = gcd(x, gcd);
        }

        int n = nums.length;
        for(int i = 0; i < n; i++) {
            if(gcd % nums[i] == 0) {
                return i;
            }
        }

        return -1;
    }

    private int gcd(int a, int b) {
        if(a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
