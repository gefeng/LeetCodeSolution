package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Valid Mountain Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/valid-mountain-array/"
)
public class Q941 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public boolean validMountainArray(int[] A) {
        if(A.length < 3)
            return false;

        int i = 0;
        while(i < A.length - 1 && A[i + 1] > A[i])
            i++;

        if(i == 0 || i == A.length - 1)
            return false;

        while(i < A.length - 1 && A[i + 1] < A[i])
            i++;

        return i == A.length - 1;
    }
}
