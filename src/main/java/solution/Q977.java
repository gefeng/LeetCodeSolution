package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Squares of a Sorted Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/squares-of-a-sorted-array/"
)
public class Q977 {
    public int[] sortedSquares(int[] A) {
        int[] ans = new int[A.length];
        int l = 0;
        int r = A.length - 1;

        for(int i = A.length - 1; i >= 0; i--) {
            if(Math.abs(A[l]) > Math.abs(A[r])) {
                ans[i] = A[l] * A[l];
                l++;
            }
            else {
                ans[i] = A[r] * A[r];
                r--;
            }
        }

        return ans;
    }
}
