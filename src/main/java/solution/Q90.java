package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Subset II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/subsets-ii/"
)
public class Q90 {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Arrays.sort(nums);
        backTrack(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void backTrack(int[] nums, int start, List<Integer> candidate, List<List<Integer>> ans) {
        ans.add(new ArrayList<>(candidate));
        for(int i = start; i < nums.length; i++) {
            candidate.add(nums[i]);
            backTrack(nums, i + 1, candidate, ans);
            candidate.remove(candidate.size() - 1);
            while(i + 1 < nums.length && nums[i] == nums[i + 1])
                i++;
        }
    }
}
