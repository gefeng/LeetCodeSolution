package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Minimize the Difference Between Target and Chosen Elements",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/minimize-the-difference-between-target-and-chosen-elements/"
)
public class Q1981 {
    public int minimizeTheDifference(int[][] mat, int target) {
        return sumLookUpSol(mat, target);
    }

    /**
     * Time:  O(N ^ 2)
     * Space: O(1)
     * */
    private int sumLookUpSol(int[][] mat, int target) {
        int m = mat.length;
        int n = mat[0].length;
        Set<Integer> from = new HashSet<>();
        from.add(0);

        for(int i = 0; i < m; i++) {
            Set<Integer> to = new HashSet<>();
            for(int j = 0; j < n; j++) {
                int ceiling = Integer.MAX_VALUE;
                for(int sum : from) {
                    if(sum + mat[i][j] >= target) {
                        ceiling = Math.min(ceiling, sum + mat[i][j]);
                    }
                }
                for(int sum : from) {
                    if(sum + mat[i][j] <= ceiling) {
                        to.add(sum + mat[i][j]);
                    }
                }
            }
            from = to;
        }

        int res = Integer.MAX_VALUE;
        for(int sum : from) {
            res = Math.min(res, Math.abs(sum - target));
        }

        return res;
    }

    /**
     * Time:  O(N ^ 2)
     * Space: O(N)
     * */
    private int topDownDpSol(int[][] mat, int target) {
        return dfs(mat, 0, 0, target, new Integer[mat.length][5000]);
    }

    private int dfs(int[][] mat, int r, int sum, int target, Integer[][] memo) {
        if(r == mat.length) {
            return Math.abs(sum - target);
        }

        if(memo[r][sum] != null) {
            return memo[r][sum];
        }

        int min = Integer.MAX_VALUE;
        for(int c = 0; c < mat[0].length; c++) {
            min = Math.min(min, dfs(mat, r + 1, sum + mat[r][c], target, memo));
        }

        return memo[r][sum] = min;
    }
}
