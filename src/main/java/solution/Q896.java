package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Monotonic Array",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/monotonic-array/"
)
public class Q896 {
    public boolean isMonotonic(int[] A) {
        if(A.length < 2)
            return true;

        int len = A.length;
        boolean increasing = (A[0] <= A[len - 1]);

        if(increasing) {
            for(int i = 0; i < len - 1; i++) {
                if(A[i] > A[i + 1])
                    return false;
            }
        } else {
            for(int i = 0; i < len - 1; i++) {
                if(A[i] < A[i + 1])
                    return false;
            }
        }

        return true;
    }

    private boolean smartSolution(int[] A) {
        if(A.length < 2)
            return true;
        boolean increasing = true;
        boolean decreasing = true;
        for(int i = 0; i < A.length - 1; i++) {
            if(A[i] > A[i + 1])
                increasing = false;
            if(A[i] < A[i + 1])
                decreasing = false;
        }

        return increasing || decreasing;
    }
}
