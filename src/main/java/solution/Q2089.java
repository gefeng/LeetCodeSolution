package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Find Target Indices After Sorting Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/find-target-indices-after-sorting-array/"
)
public class Q2089 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public List<Integer> targetIndices(int[] nums, int target) {
        List<Integer> ans = new ArrayList<>();

        Arrays.sort(nums);

        for(int i = 0; i < nums.length; i++) {
            if(nums[i] == target) {
                ans.add(i);
            }
        }

        return ans;
    }
}
