package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "N-Queens II",
        difficulty = QDifficulty.HARD,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/n-queens-ii/"
)
public class Q52 {
    int count;
    int[] rows;
    int[] diags;
    int[] aDiags;

    public int totalNQueens(int n) {
        if(n == 0 || n == 1)
            return n;

        count = 0;
        rows = new int[n];

        /*二维矩阵的对角线坐表转一维数组映射*/
        diags = new int[2 * n - 1]; // y = x + b; row - col range: [-(n - 1), (n - 1)]
        aDiags = new int[2 * n - 1]; // y = -x + b; row + col range: [0, 2n - 1]

        findSlot(0, n);

        return count;
    }

    private void findSlot(int row, int n) {
        for(int i = 0; i < n; i++) {
            if(!isUnderAttack(row, i, n)) {
                placeQueen(row, i, n);
                if(row + 1 == n)
                    count++;
                else
                    findSlot(row + 1, n);
                removeQueen(row, i, n);
            }
        }
    }

    private boolean isUnderAttack(int row, int col, int n) {
        return rows[col] + diags[row - col + n - 1] + aDiags[row + col] != 0;
    }

    public void placeQueen(int row, int col, int n) {
        rows[col] = 1;
        diags[row - col + n - 1] = 1;
        aDiags[row + col] = 1;
    }

    public void removeQueen(int row, int col, int n) {
        rows[col] = 0;
        diags[row - col + n - 1] = 0;
        aDiags[row + col] = 0;
    }
}
