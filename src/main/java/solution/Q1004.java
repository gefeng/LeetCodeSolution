package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Max Consecutive Ones III",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SLIDING_WINDOW,
        url = "https://leetcode.com/problems/max-consecutive-ones-iii/"
)
public class Q1004 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public int longestOnes(int[] A, int K) {
        int max = 0;
        int countFlip = 0;
        int l = 0;
        int r = 0;

        for(; r < A.length; r++) {
            if(A[r] == 0) {
                while(countFlip == K) {
                    countFlip = A[l] == 0 ? countFlip - 1 : countFlip;
                    l++;
                }
                countFlip++;
            }
            max = Math.max(max, r - l + 1);
        }

        return max;
    }
}
