package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Ugly Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/ugly-number/"
)
public class Q263 {
    /**
     * Time:  O(logN)
     * Space: O(1)
     * */
    public boolean isUgly(int n) {
        if(n == 0) {
            return false;
        }

        int[] primes = new int[] {2, 3, 5};

        for(int p : primes) {
            while(n % p == 0) {
                n /= p;
            }
        }

        return n == 1;
    }
}
