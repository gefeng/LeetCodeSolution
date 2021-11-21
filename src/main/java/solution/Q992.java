package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Subarrays with K Different Integers",
        difficulty = QDifficulty.HARD,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/subarrays-with-k-different-integers/"
)
public class Q992 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int subarraysWithKDistinct(int[] nums, int k) {
        return atMost(nums, k) - atMost(nums, k - 1);
    }

    private int atMost(int[] nums, int k) {
        int ans = 0;
        int n = nums.length;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int l = 0, r = 0; r < n; r++) {
            freqMap.put(nums[r], freqMap.getOrDefault(nums[r], 0) + 1);

            while(freqMap.size() > k) {
                int f = freqMap.get(nums[l]);
                if(f == 1) {
                    freqMap.remove(nums[l]);
                } else {
                    freqMap.put(nums[l], f - 1);
                }
                l++;
            }

            ans += r - l + 1;
        }

        return ans;
    }
}
