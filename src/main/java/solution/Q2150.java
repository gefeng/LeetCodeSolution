package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Find All Lonely Numbers in the Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/find-all-lonely-numbers-in-the-array/"
)
public class Q2150 {
    /**
     * Time:  O(N)
     * Space: O(N)
     **/
    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        List<Integer> ans = new ArrayList<>();

        for(int x : nums) {
            map.put(x, map.getOrDefault(x, 0) + 1);
        }

        for(int x : nums) {
            if(map.get(x) == 1 && !map.containsKey(x - 1) && !map.containsKey(x + 1)) {
                ans.add(x);
            }
        }

        return ans;
    }
}
