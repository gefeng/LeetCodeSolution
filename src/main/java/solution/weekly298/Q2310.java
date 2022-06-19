package solution.weekly298;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Sum of Numbers With Units Digit K",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.MATH,
        url = "https://leetcode.com/contest/weekly-contest-298/problems/sum-of-numbers-with-units-digit-k/"
)
public class Q2310 {
    /**
     * Time:  O(1)
     * Space: O(1)
     * */
    public int minimumNumbers(int num, int k) {
        if(num == 0) {
            return 0;
        }

        int ans = -1;
        int sum = 0;
        for(int i = 1; i <= 10; i++) {
            sum += k;
            if(sum <= num && sum % 10 == num % 10) {
                ans = i;
                break;
            }
        }

        return ans;
    }
}
