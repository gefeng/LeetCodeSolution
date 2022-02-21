package solution.weekly281;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Count Array Pairs Divisible by K",
        difficulty = QDifficulty.HARD,
        tag = QTag.MATH,
        url = "https://leetcode.com/contest/weekly-contest-281/problems/count-array-pairs-divisible-by-k/"
)
public class Q2183 {
    /**
     * Time:  O(N * sqrt(K))
     * Space: O(K)
     * */
    public long countPairs(int[] nums, int k) {
        long ans = 0;
        int n = nums.length;
        List<Integer> factor = new ArrayList<>();
        long[] cnt = new long[k + 1];

        for(int f = 1; f <= k; f++) {
            if(k % f == 0) factor.add(f);
        }

        for(int i = 0; i < n; i++) {
            ans += cnt[k / gcd(nums[i], k)];

            for(int f : factor) {
                if(nums[i] % f == 0) {
                    cnt[f]++;
                }
            }
        }

        return ans;
    }

    private int gcd(int a, int b) {
        if(a == 0) return b;
        return gcd(b % a, a);
    }
}
