package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Floored Pairs",
        difficulty = QDifficulty.HARD,
        tag = QTag.PREFIX_SUM,
        url = "https://leetcode.com/problems/sum-of-floored-pairs/"
)
public class Q1862 {
    /**
     * Time:  O(N * log(max))
     * Space: O(max)
     * */
    private static final long MOD = (long)1e9 + 7;
    private static final int MAX = (int)1e5;
    public int sumOfFlooredPairs(int[] nums) {
        long ans = 0;
        int n = nums.length;
        int[] freq = new int[MAX + 1];
        int[] psum = new int[MAX + 1]; // prefix sum of frequency

        for(int x : nums) {
            freq[x]++;
        }

        for(int i = 1; i <= MAX; i++) {
            psum[i] = psum[i - 1] + freq[i];
        }

        for(int i = 0; i <= MAX; i++) {
            if(freq[i] == 0) continue;

            for(int k = 1; k * i <= MAX; k++) {
                int lb = k * i;
                int ub = Math.min((k + 1) * i - 1, MAX);
                long tot = psum[ub] - psum[lb - 1];

                ans = (ans + (tot * k % MOD) * freq[i] % MOD) % MOD;
            }
        }

        return (int)ans;
    }
}
