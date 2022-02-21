package solution.weekly281;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Integers With Even Digit Sum",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/contest/weekly-contest-281/problems/count-integers-with-even-digit-sum/"
)
public class Q2180 {
    /**
     * Time:  O(N * logN)
     * Space: O(1)
     * */
    public int countEven(int num) {
        int ans = 0;
        for(int i = 1; i <= num; i++) {
            int x = i;
            int sum = 0;
            while(x != 0) {
                sum += x % 10;
                x /= 10;
            }

            if(sum % 2 == 0) {
                ans++;
            }
        }

        return ans;
    }
}
