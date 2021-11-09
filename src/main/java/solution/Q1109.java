package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Map;
import java.util.TreeMap;

@Problem(
        title = "Corporate Flight Bookings",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/corporate-flight-bookings/"
)
public class Q1109 {
    /**
     * 1. for each triplet [i, j, k]  increase booking[i] by k and decrease booking[j + 1] by -k
     * 2. sweep line and accumulate total bookings
     *
     * Time:  O(N)
     * Space: O(N)
     * */
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] res = new int[n];

        for(int[] b : bookings) {
            res[b[0] - 1] += b[2];
            if(b[1] < n) {
                res[b[1]] -= b[2];
            }
        }

        int sum = 0;
        for(int i = 0; i < n; i++) {
            sum += res[i];
            res[i] = sum;
        }
        return res;
    }
}
