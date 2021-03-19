package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Combination Sum",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/combination-sum/"
)
public class Q39 {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void backTrack(int[] candidates, int target, int start, List<Integer> seq, List<List<Integer>> ans) {
        if(target == 0) {
            ans.add(new ArrayList<>(seq));
            return;
        }

        for(int i = start; i < candidates.length; i++) {
            int remain = target - candidates[i];
            if(remain >= 0) {
                seq.add(candidates[i]);
                backTrack(candidates, remain, i, seq, ans);
                seq.remove(seq.size() - 1);
            }
        }
    }
}
