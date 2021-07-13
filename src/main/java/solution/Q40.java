package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Combination Sum II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/combination-sum-ii/"
)
public class Q40 {
    /*
    * sort the array first,
    * pick or not pick to build combinations
    * skip duplicated elements to avoid duplicated combinations.
    * */
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);

        List<List<Integer>> ans = new ArrayList<>();

        dfs(candidates, target, 0, new ArrayList<>(), ans);

        return ans;
    }

    private void dfs(int[] nums, int target, int idx, List<Integer> cand, List<List<Integer>> ans) {
        int n = nums.length;
        if(idx == n) {
            if(target == 0) {
                ans.add(new ArrayList<>(cand));
            }
            return;
        }

        if(target - nums[idx] >= 0) {
            cand.add(nums[idx]);
            dfs(nums, target - nums[idx], idx + 1, cand, ans);
            cand.remove(cand.size() - 1);
        }

        while(idx < n - 1 && nums[idx] == nums[idx + 1]) {
            idx++;
        }
        dfs(nums, target, idx + 1, cand, ans);
    }
}
