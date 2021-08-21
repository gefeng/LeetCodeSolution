package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Increasing Subsequence",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/increasing-subsequences/"
)
public class Q491 {
    /**
     * Similar to finding subsets.
     *
     * Time:  O(2 ^ N * N)
     * Space: O(2 ^ N * N)
     * */
    public List<List<Integer>> findSubsequences(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();

        dfs(nums, 0, new ArrayList<>(), res);

        return res;
    }

    private void dfs(int[] nums, int i, List<Integer> seq, List<List<Integer>> res) {
        if(seq.size() > 1) {
            res.add(new ArrayList<>(seq));
        }

        boolean[] seen = new boolean[201];
        for(int j = i; j < nums.length; j++) {
            if(seen[nums[j] + 100]) {
                continue;
            }
            seen[nums[j] + 100] = true;

            if(seq.size() == 0 || seq.get(seq.size() - 1) <= nums[j]) {
                seq.add(nums[j]);
                dfs(nums, j + 1, seq, res);
                seq.remove(seq.size() - 1);
            }
        }
    }
}
