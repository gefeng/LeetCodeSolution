package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sort Array By Parity",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/sort-array-by-parity/"
)
public class Q905 {
    public int[] sortArrayByParity(int[] A) {
        for(int rPointer = 0, wPointer = 0; rPointer < A.length; rPointer++) {
            if(A[rPointer] % 2 == 0) {
                int temp = A[rPointer];
                A[rPointer] = A[wPointer];
                A[wPointer++] = temp;
            }
        }
        return A;
    }
}
