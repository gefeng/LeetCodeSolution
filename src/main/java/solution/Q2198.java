package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Single Divisor Triplets",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/number-of-single-divisor-triplets/"
)
public class Q2198 {
    /**
     * Time:  O(max(nums) ^ 3)
     * Space: O(max(nums))
     * */
    public long singleDivisorTriplet(int[] nums) {
        int n = nums.length;
        long[] cnt = new long[101];
        long ans = 0;

        for(int x : nums) cnt[x]++;

        for(int i = 1; i <= 100; i++) {
            if(cnt[i] == 0) continue;
            for(int j = i; j <= 100; j++) {
                if(cnt[j] == 0) continue;
                if(i == j && cnt[i] == 1) continue;
                for(int k = j; k <= 100; k++) {
                    if(cnt[k] == 0) continue;
                    if((i == k && cnt[i] == 1) || (j == k && cnt[j] == 1)) continue;

                    int sum = i + j + k;
                    int rem0 = 0;
                    if(sum % i == 0) rem0++;
                    if(sum % j == 0) rem0++;
                    if(sum % k == 0) rem0++;
                    if(rem0 == 1) {

                        if(i == j) {
                            ans += cnt[k] * (cnt[i] * (cnt[i] - 1) / 2) * 6;
                        } else if(i == k) {
                            ans += cnt[j] * (cnt[i] * (cnt[i] - 1) / 2) * 6;
                        } else if(j == k) {
                            ans += cnt[i] * (cnt[j] * (cnt[j] - 1) / 2) * 6;
                        } else {
                            ans += cnt[i] * cnt[j] * cnt[k] * 6;
                        }
                    }
                }
            }
        }

        return ans;
    }
}
