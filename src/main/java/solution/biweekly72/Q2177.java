package solution.biweekly72;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Find Three Consecutive Integers That Sum to a Given Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/contest/biweekly-contest-72/problems/find-three-consecutive-integers-that-sum-to-a-given-number/"
)
public class Q2177 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public long[] sumOfThree(long num) {
        long[] ans = new long[3];
        if(num % 3 == 0) {
            return new long[] {num / 3 - 1, num / 3, num / 3 + 1};
        } else {
            return new long[0];
        }
    }
}
