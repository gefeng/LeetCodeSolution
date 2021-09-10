package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Average Salary Excluding the Minimum and Maximum Salary",
        difficulty = QDifficulty.EASY,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/average-salary-excluding-the-minimum-and-maximum-salary/"
)
public class Q1491 {
    /**
     * Time:  O(N)
     * Space: O(1)
     * */
    public double average(int[] salary) {
        int sum = 0;
        int min = salary[0];
        int max = salary[0];

        for(int s : salary) {
            sum += s;
            min = Math.min(min, s);
            max = Math.max(max, s);
        }

        return (double)(sum - min - max) / (salary.length - 2);
    }
}
