package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Factor Combinations",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/factor-combinations/"
)
public class Q254 {
    public List<List<Integer>> getFactors(int n) {
        List<List<Integer>> ans = new ArrayList<>();

        dfs(2, n, new ArrayList<>(), ans);

        return ans;
    }

    private void dfs(int factor, int n, List<Integer> cand, List<List<Integer>> ans) {
        if(n > 1 && !cand.isEmpty()) {
            List<Integer> comb = new ArrayList<>(cand);
            comb.add(n);
            ans.add(comb);
        }

        for(int f = factor; f * f <= n; f++) {
            if(n % f == 0) {
                cand.add(f);
                dfs(f, n / f, cand, ans);
                cand.remove(cand.size() - 1);
            }
        }
    }
}
