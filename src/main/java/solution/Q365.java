package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Water and Jug Problem",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/water-and-jug-problem/"
)
public class Q365 {
    /**
     * Time:  O(logN)
     * Space: O(logN)
     * */
    public boolean canMeasureWater(int jug1Capacity, int jug2Capacity, int targetCapacity) {
        int a = Math.min(jug1Capacity, jug2Capacity);
        int b = Math.max(jug1Capacity, jug2Capacity);
        int c = targetCapacity;

        if(a + b < c) {
            return false;
        }

        return c % gcd(a, b) == 0;
    }

    private int gcd(int a, int b) {
        if(a == 0) {
            return b;
        }
        return gcd(b % a, a);
    }
}
