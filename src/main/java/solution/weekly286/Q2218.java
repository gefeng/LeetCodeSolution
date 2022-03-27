package solution.weekly286;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Maximum Value of K Coins From Piles",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/contest/weekly-contest-286/problems/maximum-value-of-k-coins-from-piles/"
)
public class Q2218 {
    /**
     * Time:  O(N * sum(piles[i].length))
     * Space: O(N * K)
     * */
    public int maxValueOfCoins(List<List<Integer>> piles, int k) {
        int n = piles.size();

        List<Integer>[] psum = new List[n];

        for(int i = 0; i < n; i++) {
            List<Integer> p = piles.get(i);
            List<Integer> sum = new ArrayList<>();

            int rolling = 0;
            sum.add(0);
            for(int j = 1; j <= p.size(); j++) {
                rolling += p.get(j - 1);
                sum.add(rolling);
            }

            psum[i] = sum;
        }

        return dfs(piles, psum, k, 0, new Integer[n + 1][k + 1]);
    }

    private int dfs(List<List<Integer>> piles, List<Integer>[] psum, int k, int cur, Integer[][] memo) {
        int n = piles.size();

        if(cur == n) return 0;
        if(k == 0) return 0;

        if(memo[cur][k] != null) return memo[cur][k];

        List<Integer> p = piles.get(cur);
        int skip = dfs(piles, psum, k, cur + 1, memo);
        int pick = 0;

        int tot = p.size();
        for(int i = 1; i <= Math.min(k, tot); i++) {
            int score = psum[cur].get(i);
            pick = Math.max(pick, dfs(piles, psum, k - i, cur + 1, memo) + score);
        }

        return memo[cur][k] = Math.max(skip, pick);
    }
}
