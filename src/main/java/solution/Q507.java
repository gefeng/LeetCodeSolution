package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Perfect Number",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/perfect-number/"
)
public class Q507 {
    /**
     * Time:  O(sqrt(N))
     * Space: O(1)
     * */
    public boolean checkPerfectNumber(int num) {
        int sum = 0;
        for(int i = 2; i * i <= num; i++) {
            if(num % i == 0) {
                sum += i;
                sum += num / i;
            }
        }

        sum = num == 1 ? sum : sum + 1;

        return sum == num;
    }
}
