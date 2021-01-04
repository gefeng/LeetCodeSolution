package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Combination Sum III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/combination-sum-iii/"
)
public class Q216 {
    List<List<Integer>> ans;
    List<Integer> comb;
    public List<List<Integer>> combinationSum3(int k, int n) {
        ans = new ArrayList<>();
        comb = new ArrayList<>();
        backTrack(k, n, 1, 0);
        return ans;
    }

    private void backTrack(int k, int n, int start, int sum) {
        if(comb.size() == k) {
            if(sum == n) {
                ans.add(new ArrayList<>(comb));
            }
            return;
        }
        for(int i = start; i <= 9; i++) {
            comb.add(i);
            backTrack(k, n, i + 1, sum + i);
            comb.remove(comb.size() - 1);
        }
    }
}
