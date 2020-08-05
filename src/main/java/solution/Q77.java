package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "Combinations",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/combinations/"
)
public class Q77 {
    List<Integer> comb = new ArrayList<>();
    List<List<Integer>> ans = new ArrayList<>();

    public List<List<Integer>> combine(int n, int k) {
        backTrack(n, k, 1);
        return ans;
    }

    private void backTrack(int n, int k, int start) {
        for(int i = start; i <= n; i++) {
            comb.add(i);
            if (comb.size() == k)
                ans.add(new ArrayList<>(comb));
            else
                backTrack(n, k, i + 1);
            comb.remove(comb.size() - 1);
        }
    }
}
