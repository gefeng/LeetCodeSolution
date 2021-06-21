package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Minimum Number of Operations to Move All Balls to Each Box",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/minimum-number-of-operations-to-move-all-balls-to-each-box/"
)
public class Q1769 {
    /*
        two passes

        move all the balls before i to box i
        move all the balls after i to box i

        for each round, number of operations increased by total number of balls so far
    */
    public int[] minOperations(String boxes) {
        int n = boxes.length();
        int[] ans = new int[n];

        int cntOnes = 0;
        int cntOper = 0;
        for(int i = 0; i < n; i++) {
            cntOper += cntOnes;
            ans[i] = cntOper;
            cntOnes = boxes.charAt(i) == '1' ? cntOnes + 1 : cntOnes;
        }

        cntOnes = 0;
        cntOper = 0;
        for(int i = n - 1; i >= 0; i--) {
            cntOper += cntOnes;
            ans[i] += cntOper;
            cntOnes = boxes.charAt(i) == '1' ? cntOnes + 1 : cntOnes;
        }

        return ans;
    }
}
