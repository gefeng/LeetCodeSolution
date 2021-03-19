package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Subsets",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/subsets/"
)
public class Q78 {
    /*
    * Find subsets normally requires O(2^n)
    * Each element can be present or not
    * And we need to copy the candidate so time: O(2^n * n)
    * */
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        ans.add(new ArrayList<>());
        backTrack(nums, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void backTrack(int[] nums, int start, List<Integer> candidate, List<List<Integer>> ans) {
        for(int i = start; i < nums.length; i++) {
            candidate.add(nums[i]);
            ans.add(new ArrayList<>(candidate));
            backTrack(nums, i + 1, candidate, ans);
            candidate.remove(candidate.size() - 1);
        }
    }
}
