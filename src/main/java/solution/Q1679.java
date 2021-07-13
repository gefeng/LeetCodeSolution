package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Max Number of K-Sum Pairs",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/max-number-of-k-sum-pairs/"
)
public class Q1679 {
    public int maxOperations(int[] nums, int k) {
        int n = nums.length;
        int ans = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int i = 0; i < n; i++) {
            Integer freq = freqMap.get(k - nums[i]);
            int diff = k - nums[i];
            if(freq == null) {
                freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);
            } else {
                if(freq == 1) {
                    freqMap.remove(diff);
                } else {
                    freqMap.put(diff, freq - 1);
                }
                ans++;
            }
        }

        return ans;
    }
}
