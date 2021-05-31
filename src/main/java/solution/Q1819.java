package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Different Subsequences GCDs",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/number-of-different-subsequences-gcds/"
)
public class Q1819 {
    public int countDifferentSubsequenceGCDs(int[] nums) {
        int n = nums.length;
        int m = 2 * (int)1e5 + 1;

        int gcd[] = new int[m];
        int max = 0;
        for(int i = 0; i < n; i++) {
            max = Math.max(max, nums[i]);
            for(int j = 1; j * j < m; j++) {
                if(nums[i] % j == 0) {
                    int f1 = j;
                    int f2 = nums[i] / j;

                    gcd[f1] = gcd(gcd[f1], nums[i]);
                    gcd[f2] = gcd(gcd[f2], nums[i]);
                }
            }
        }

        int cnt = 0;
        for(int i = 1; i <= max; i++) {
            if(gcd[i] == i) {
                cnt++;
            }
        }
        return cnt;
    }

    private int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a %  b);
    }
}
