package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Three Divisors",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/three-divisors/"
)
public class Q1952 {
    /**
     * n must be a perfect square number with no other divisors except 1 and itself.
     * 
     * Time:  O(sqrt(N))
     * Space: O(1)
     * */
    public boolean isThree(int n) {
        int cnt = 0;
        int i = 2;
        while(i * i < n) {
            if(n % i == 0) {
                return false;
            }
            i++;
        }

        return i * i == n;
    }
}
