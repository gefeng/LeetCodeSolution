package solution.weekly296;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.Map;

@Problem(
        title = "Replace Elements in an Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/contest/weekly-contest-296/problems/replace-elements-in-an-array/"
)
public class Q2295 {
    /**
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] arrayChange(int[] nums, int[][] operations) {
        Map<Integer, Integer> map = new HashMap<>();
        int n = nums.length;

        for(int i = 0; i < n; i++) {
            map.put(nums[i], i);
        }

        for(int[] op : operations) {
            int x = op[0];
            int y = op[1];

            int idx = map.get(x);
            map.remove(x);
            map.put(y, idx);

            nums[idx] = y;
        }

        return nums;
    }
}
