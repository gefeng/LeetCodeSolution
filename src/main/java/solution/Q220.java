package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.TreeSet;

@Problem(
        title = "Contains Duplicate III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/contains-duplicate-iii/"
)
public class Q220 {
    /**
     * |i - j| <= k and |nums[i] - nums[j] <= t|
     * 这题有个有趣的点就是越界问题，比如如果这么写ceil  <= nums[i] + t 那么nums[i] + t 就有可能越界。
     * cast to long before comparison to solve the issue.
     */
    public boolean containsNearbyAlmostDuplicate(int[] nums, int k, int t) {
        TreeSet<Integer> set = new TreeSet<>();
        for(int i = 0; i < nums.length; i++) {
            Integer ceiling = set.ceiling(nums[i]);
            if(ceiling != null && ceiling <= (long)nums[i] + t)
                return true;
            Integer floor = set.floor(nums[i]);
            if(floor != null && floor >= (long)nums[i] - t)
                return true;
            set.add(nums[i]);
            if(set.size() > k)
                set.remove(nums[i - k]);
        }
        return false;
    }
}
