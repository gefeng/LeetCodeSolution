package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Make Sum Divisible by P",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/make-sum-divisible-by-p/"
)
public class Q1590 {
    /**
     * (sum(arr) - sum(arr[i, j])) % p == 0
     * => sum(arr[i, j]) % p == sum(arr) % p
     * => (sum(arr[0, j]) - sum(arr[0, i])) % p == remainder
     * => sum(arr[0, i]) % p == (sum(arr[0, j]) - remainder + p) % p (negative number modular in Java)
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int minSubarray(int[] nums, int p) {
        int n = nums.length;
        int res = n;

        long sum = 0;
        for(int num : nums) {
            sum += num;
        }

        long rem = sum % p;
        if(rem == 0) {
            return 0;
        }

        Map<Long, Integer> map = new HashMap<>();
        map.put(0L, -1);
        sum = 0;

        for(int i = 0; i < n; i++) {
            sum += nums[i];

            Integer idx = map.get((sum - rem) % p);
            if(idx != null) {
                res = Math.min(res, i - idx);
            }

            map.put(sum % p, i);
        }

        return res == n ? -1 : res;
    }
}
