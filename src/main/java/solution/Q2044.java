package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Count Number of Maximum Bitwise-OR Subsets",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/count-number-of-maximum-bitwise-or-subsets/"
)
public class Q2044 {
    /**
     * Time:  O(2 ^ N)
     * Space: O(2 ^ N)
     * */
    Map<Integer, Integer> freqMap = new HashMap<>();
    public int countMaxOrSubsets(int[] nums) {
        dfs(nums, 0, 0);

        int max = 0;
        for(int key : freqMap.keySet()) {
            max = Math.max(max, key);
        }

        return freqMap.get(max);
    }

    private void dfs(int[] nums, int cur, int mask) {
        if(cur == nums.length) {
            if(mask != 0) {
                int sum = 0;
                for(int i = 0; i < nums.length; i++) {
                    if(((1 << i) & mask) != 0) {
                        sum |= nums[i];
                    }
                }
                freqMap.put(sum, freqMap.getOrDefault(sum, 0) + 1);
            }

            return;
        }

        dfs(nums, cur + 1, mask);
        dfs(nums, cur + 1, mask | (1 << cur));
    }
}
