package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "Permutations II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/permutations-ii/"
)
public class Q47 {
    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);

        List<List<Integer>> ans = new ArrayList<>();

        dfs(nums, new boolean[nums.length], new ArrayList<>(), ans);

        return ans;
    }

    private void dfs(int[] nums, boolean[] seen, List<Integer> perm, List<List<Integer>> ans) {
        int n = nums.length;

        if(perm.size() == n) {
            ans.add(new ArrayList<>(perm));
            return;
        }

        for(int i = 0; i < n; i++) {
            if(seen[i]) {
                continue;
            }

            perm.add(nums[i]);
            seen[i] = true;
            dfs(nums, seen, perm, ans);
            perm.remove(perm.size() - 1);
            seen[i] = false;

            while(i < n - 1 && nums[i] == nums[i + 1]) {
                i++;
            }
        }
    }
}
