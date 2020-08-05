package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "N-Queens",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/n-queens/"
)
public class Q51 {
    List<List<String>> ans;
    int[] queen;
    int[] rows;
    int[] diags;
    int[] aDiags;
    public List<List<String>> solveNQueens(int n) {
        ans = new ArrayList<>();
        if(n == 0)
            return ans;

        queen = new int[n];
        rows = new int[n];
        diags = new int[2 * n - 1];
        aDiags = new int[2 * n - 1];
        backTrack(0, n);

        return ans;
    }

    private void backTrack(int row, int n) {
        for(int i = 0; i < n; i++) {
            if(isNotUnderAttack(row, i, n)) {
                placeQueen(row, i, n);
                if(row + 1 == n) {
                    addToSolution(n);
                }
                else
                    backTrack(row + 1 , n);
                removeQueen(row, i, n);
            }
        }
    }

    private boolean isNotUnderAttack(int row, int col , int n) {
        return rows[col] + diags[row - col + n - 1] + aDiags[row + col] == 0;
    }

    private void placeQueen(int row, int col, int n) {
        queen[row] = col;
        rows[col] = 1;
        diags[row - col + n - 1] = 1;
        aDiags[row + col] = 1;
    }

    private void removeQueen(int row, int col, int n) {
        queen[row] = 0;
        rows[col] = 0;
        diags[row - col + n - 1] = 0;
        aDiags[row + col] = 0;
    }

    private void addToSolution(int n) {
        List<String> sol = new ArrayList<>();
        for(int i = 0; i < n; i++) {
            StringBuilder sb = new StringBuilder();
            for(int j = 0; j < n; j++) {
                if(queen[i] == j)
                    sb.append('Q');
                else
                    sb.append('.');
            }
            sol.add(sb.toString());
        }
        ans.add(sol);
    }
}
