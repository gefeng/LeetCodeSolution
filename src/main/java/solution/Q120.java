package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.List;

@Problem(
        title = "Triangle",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/triangle/"
)
public class Q120 {
    public int minimumTotal(List<List<Integer>> triangle) {
        return recursiveSolution(triangle);
    }

    private int recursiveSolution(List<List<Integer>> triangle) {
        int numRows = triangle.size();
        return dfsSumPath(triangle, 0, 0, new Integer[numRows][triangle.get(numRows - 1).size()]);
    }

    private int dfsSumPath(List<List<Integer>> triangle, int row, int col, Integer[][] memo) {
        List<Integer> rowValues = triangle.get(row);

        if(col > rowValues.size() - 1)
            return Integer.MAX_VALUE;
        if(row == triangle.size() - 1)
            return rowValues.get(col);

        if(memo[row][col] != null)
            return memo[row][col];

        int min = 0;
        min = Math.min(dfsSumPath(triangle, row + 1, col, memo), dfsSumPath(triangle, row + 1, col + 1, memo));


        return memo[row][col] = min + rowValues.get(col);
    }

    private int dpSolution(List<List<Integer>> triangle) {
        int min = Integer.MAX_VALUE;
        int numRow = triangle.size();
        int[] dp = new int[triangle.get(numRow - 1).size()];

        for(int i = 0; i < numRow; i++) {
            List<Integer> row = triangle.get(i);
            for(int j = row.size() - 1; j >= 0; j--) {
                if(j == 0)
                    dp[j] = dp[j] + row.get(j);
                else if (j == row.size() - 1)
                    dp[j] = dp[j - 1] + row.get(j);
                else
                    dp[j] = Math.min(dp[j], dp[j - 1]) + row.get(j);

                if(i == numRow - 1) {
                    min = Math.min(min, dp[j]);
                }
            }
        }

        return min;
    }
}
