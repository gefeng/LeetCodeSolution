package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Number of Steps to Reduce a Number to Zero",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/number-of-steps-to-reduce-a-number-to-zero/"
)
public class Q1342 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int numberOfSteps(int num) {
        int rMost = 0;
        int cntOne = 0;
        for(int i = 0; i < 20; i++) {
            if(((1 << i) & num) != 0) {
                cntOne++;
                rMost = i;
            }
        }

        return rMost + cntOne;
    }
}
