package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Average Waiting Time",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/average-waiting-time/"
)
public class Q1701 {
    public double averageWaitingTime(int[][] customers) {
        int n = customers.length;
        long wait = 0;
        long available = 0;
        for(int[] c : customers) {
            if(c[0] >= available) {
                available = c[0] + c[1];
                wait += c[1];
            } else {
                available += c[1];
                wait += available - c[0];
            }
        }

        return (double)wait / n;
    }
}
