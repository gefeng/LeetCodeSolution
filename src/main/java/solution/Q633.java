package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Sum of Square Numbers",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/sum-of-square-numbers/"
)
public class Q633 {
    public boolean judgeSquareSum(int c) {
        return twoPointsSol(c);
    }

    /**
     * Time:  O(N ^ -2)
     * Space: O(N ^ -2)
     * */
    private boolean setSol(int c) {
        Set<Integer> sqrNums = new HashSet<>();
        for(int i = 0; i * i <= c; i++) {
            sqrNums.add(i * i);
        }

        for(int sn : sqrNums) {
            if(sqrNums.contains(c - sn)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Time:  O(N ^ -2)
     * Space: O(1)
     * */
    private boolean twoPointsSol(int c) {
        long l = 0;
        long r = (long)Math.sqrt(c);

        while(l <= r) {
            long sum = l * l + r * r;

            if(sum == c) {
                return true;
            } else if(sum > c) {
                r--;
            } else {
                l++;
            }
        }

        return false;
    }
}
