package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Subarray Sums Divisible by K",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/subarray-sums-divisible-by-k/"
)
public class Q974 {
    /**
     * Time:  O(N)
     * Space: O(K)
     * */
    public int subarraysDivByK(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum = (sum + nums[i] % k + k) % k;
            ans += map.getOrDefault(sum, 0);

            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }

        return ans;
    }
}
