package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Third Maximum Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/third-maximum-number/"
)
public class Q414 {
    public int thirdMax(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for(int n : nums) {
            set.add(n);
            if(set.size() > 3)
                set.remove(Collections.min(set));
        }

        return set.size() < 3 ? Collections.max(set) : Collections.min(set);
    }
}
