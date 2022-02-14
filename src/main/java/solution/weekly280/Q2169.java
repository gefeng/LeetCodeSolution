package solution.weekly280;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Count Operations to Obtain Zero",
        difficulty = QDifficulty.EASY,
        tag = QTag.SIMULATION,
        url = "https://leetcode.com/contest/weekly-contest-280/problems/count-operations-to-obtain-zero/"
)
public class Q2169 {
    /**
     * Time:  O(log(min(num1, num2)))
     * Space: O(1)
     * */
    public int countOperations(int num1, int num2) {
        int ans = 0;
        while(num1 != 0 && num2 != 0) {
            if(num1 >= num2) {
                ans += num1 / num2;
                num1 = num1 % num2;
            } else {
                ans += num2 / num1;
                num2 = num2 % num1;
            }
        }

        return ans;
    }
}
