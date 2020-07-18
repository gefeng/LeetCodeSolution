package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.HashSet;

@Problem(
        title = "Contains Duplicate II",
        difficulty = QDifficulty.EASY,
        tag = QTag.HASH_TABLE,
        url = "https://leetcode.com/problems/contains-duplicate-ii/"
)
public class Q219 {
    public boolean containsNearbyDuplicate(int[] nums, int k) {
        HashMap<Integer, Integer> map = new HashMap<>();
        for(int i = 0; i < nums.length; i++) {
            if(map.containsKey(nums[i])) {
                int dist = i - map.get(nums[i]);
                if(dist <= k)
                    return true;
            }
            map.put(nums[i], i);
        }
        return false;
    }

    /*less space using sliding window*/
    public boolean containsNearbyDuplicateHashSet(int[] nums, int k) {
        HashSet<Integer> set = new HashSet<>();
        for(int i = 0; i < nums.length; i++) {
            if(set.contains(nums[i]))
                return true;
            set.add(nums[i]);
            if(set.size() >= k)
                set.remove(nums[i - k]);
        }
        return false;
    }
}
