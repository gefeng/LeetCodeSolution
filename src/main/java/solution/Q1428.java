package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.List;

@Problem(
        title = "Leftmost Column with at Least a One",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/leftmost-column-with-at-least-a-one/"
)
public class Q1428 {
    interface BinaryMatrix {
        public int get(int row, int col);
        public List<Integer> dimensions();
    }
    public int leftMostColumnWithOne(BinaryMatrix binaryMatrix) {
        //return binarySearchSolution(binaryMatrix);
        return smartSolution(binaryMatrix);
    }

    private int smartSolution(BinaryMatrix binaryMatrix) {
        List<Integer> d = binaryMatrix.dimensions();
        int m = d.get(0);
        int n = d.get(1);
        int firstCol = -1;

        int r = 0;
        int c = n - 1;
        while(r < m && c >= 0) {
            int res = binaryMatrix.get(r, c);
            if(res == 1) {
                firstCol = c;
                c--;
            } else {
                r++;
            }
        }
        return firstCol;
    }

    private int binarySearchSolution(BinaryMatrix binaryMatrix) {
        List<Integer> d = binaryMatrix.dimensions();
        int m = d.get(0);
        int n = d.get(1);
        int firstCol = n;

        for(int i = 0; i < m; i++) {
            int col = binarySearchFirstOne(binaryMatrix, m, n, i);
            if(col != -1) {
                firstCol = Math.min(firstCol, col);
            }
        }

        return firstCol == n ? -1 : firstCol;
    }

    private int binarySearchFirstOne(BinaryMatrix binaryMatrix, int m, int n, int row) {
        int lo = 0;
        int hi = n - 1;
        int mid = 0;
        int col = -1;
        while(lo <= hi) {
            mid = lo + (hi - lo) / 2;
            if(binaryMatrix.get(row, mid) == 1) {
                col = mid;
                hi = mid - 1;
            } else {
                lo = mid + 1;
            }
        }

        return col;
    }
}
