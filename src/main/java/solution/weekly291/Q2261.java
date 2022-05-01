package solution.weekly291;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "K Divisible Elements Subarrays",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ROLLING_HASH,
        url = "https://leetcode.com/contest/weekly-contest-291/problems/k-divisible-elements-subarrays/"
)
public class Q2261 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    public int countDistinct(int[] nums, int k, int p) {
        int n = nums.length;
        int ans = 0;

        for(int i = 1; i <= n; i++) {
            long base = 200;
            long mod = (long)1e9 + 7;
            long d = 1;
            long hash = 0;
            Set<Long> set = new HashSet<>();
            int cnt = 0;
            for(int l = 0, r = 0; r < n; r++) {
                if(nums[r] % p == 0) {
                    cnt++;
                }
                hash = (hash * base % mod + nums[r]) % mod;

                if(r - l + 1 > i) {
                    hash = (hash - d * nums[l] % mod + mod) % mod;
                    if(nums[l] % p == 0) {
                        cnt--;
                    }
                    l++;
                } else {
                    d = d * base % mod;
                }

                if(r - l + 1 == i) {
                    if(cnt <= k) {
                        if(!set.contains(hash)) {
                            ans++;
                            set.add(hash);
                        }
                    }
                }
            }
        }

        return ans;
    }
}
