package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Maximum Number of Non-Overlapping Subarrays With Sum Equals Target",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/maximum-number-of-non-overlapping-subarrays-with-sum-equals-target/"
)
public class Q1546 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int maxNonOverlapping(int[] nums, int target) {
        int n = nums.length;
        int res = 0;
        int sum = 0;
        int last = -1;
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);

        for(int i = 0; i < n; i++) {
            sum += nums[i];

            if(map.containsKey(sum - target)) {
                int l = map.get(sum - target) + 1;
                if(l > last) {
                    last = i;
                    res++;
                }
            }

            map.put(sum, i);
        }

        return res;
    }
}
