package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Longest Harmonious Subsequence",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/longest-harmonious-subsequence/"
)
public class Q594 {
    public int findLHS(int[] nums) {
        return hashmapSol(nums);
    }

    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    private int sortSol(int[] nums) {
        int n = nums.length;
        int res = 0;

        Arrays.sort(nums);

        int l = 0;
        int r = 0;
        int diff = 0;

        while(r < n) {
            if(r != 0 && nums[r] != nums[r - 1]) {
                diff++;
            }

            while(diff > 1) {
                l++;
                if(nums[l] != nums[l - 1]) {
                    diff--;
                }
            }

            if(diff == 1 && nums[r] - nums[l] == 1) {
                res = Math.max(res, r - l + 1);
            }

            r++;
        }

        return res;
    }

    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    private int hashmapSol(int[] nums) {
        int n = nums.length;
        int res = 0;

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int x = map.getOrDefault(nums[i], 0);
            int y = map.getOrDefault(nums[i] + 1, 0);
            int z = map.getOrDefault(nums[i] - 1, 0);

            if(y != 0) {
                res = Math.max(res, x + y + 1);
            }
            if(z != 0) {
                res = Math.max(res, x + z + 1);
            }

            map.put(nums[i], x + 1);
        }

        return res;
    }
}
