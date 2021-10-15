package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Divide Array in Sets of K Consecutive Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/divide-array-in-sets-of-k-consecutive-numbers/"
)
public class Q1296 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public boolean isPossibleDivide(int[] nums, int k) {
        int n = nums.length;

        Arrays.sort(nums);

        Map<Integer, Integer> map = new HashMap<>();
        for(int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        for(int i = 0; i < n; i++) {
            if(map.get(nums[i]) > 0) {
                for(int j = 0; j < k; j++) {
                    int cnt = map.getOrDefault(nums[i] + j, 0);
                    if(cnt == 0) {
                        return false;
                    }
                    map.put(nums[i] + j, cnt - 1);
                }
            }
        }

        return true;
    }
}
