package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;

@Problem(
        title = "Two Sum",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/two-sum/"
)
public class Q1 {
    public int[] twoSum(int[] nums, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            Integer index = map.get(target - nums[i]);
            if(index != null)
                return new int[] {index, i};
            map.put(nums[i], i);
        }
        return new int[] {-1, -1};
    }
}
