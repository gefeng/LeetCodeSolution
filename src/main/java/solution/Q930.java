package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Binary Subarrays With Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/binary-subarrays-with-sum/"
)
public class Q930 {
    public int numSubarraysWithSum(int[] nums, int goal) {
        return transformToAtMostK(nums, goal);
    }

    private int countPrefixSum(int[] nums, int goal) {
        int n = nums.length;
        int prefixSum = 0;
        int cnt = 0;
        Map<Integer, Integer> freqMap = new HashMap<>();

        for(int i = 0; i < n; i++) {
            prefixSum += nums[i];

            cnt = prefixSum == goal ? cnt + 1 : cnt;

            cnt += freqMap.getOrDefault(prefixSum - goal, 0);

            freqMap.put(prefixSum, freqMap.getOrDefault(prefixSum, 0) + 1);
        }

        return cnt;
    }

    private int transformToAtMostK(int[] nums, int goal) {
        return atMostK(nums, goal) - atMostK(nums, goal - 1);
    }

    private int atMostK(int[] nums, int k) {
        if(k < 0) {
            return 0;
        }

        int cntOne = 0;
        int cnt = 0;
        int l = 0;
        int r = 0;

        while(r < nums.length) {
            cntOne += nums[r];

            while(cntOne > k) {
                cntOne -= nums[l++];
            }

            cnt += (r - l + 1);
            r++;
        }

        return cnt;
    }
}
