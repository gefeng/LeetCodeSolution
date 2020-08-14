package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Find All Numbers Disappeared in an Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/find-all-numbers-disappeared-in-an-array/"
)
public class Q448 {
    public List<Integer> findDisappearedNumbers(int[] nums) {
        List<Integer> ans = new ArrayList<>();
        for(int i = 0; i < nums.length; i++) {
            int j = Math.abs(nums[i]) - 1;
            if(nums[j] > 0)
                nums[j] *= -1;
        }

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] > 0)
                ans.add(i + 1);
        }

        return ans;
    }
}
