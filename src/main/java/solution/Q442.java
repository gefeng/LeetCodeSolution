package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Find All Duplicates in an Array",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-all-duplicates-in-an-array/"
)
public class Q442 {
    public List<Integer> findDuplicates(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            int j = Math.abs(nums[i]) - 1;
            if(nums[j] > 0)
                nums[j] *= -1;
            else
                ans.add(Math.abs(nums[i]));
        }

        return ans;
    }
}
