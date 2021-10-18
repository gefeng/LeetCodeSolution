package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Number of Moves to Seat Everyone",
        difficulty = QDifficulty.EASY,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/minimum-number-of-moves-to-seat-everyone/"
)
public class Q2037 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int minMovesToSeat(int[] seats, int[] students) {
        int ans = 0;
        int n = seats.length;

        Arrays.sort(seats);
        Arrays.sort(students);

        for(int i = 0; i < n; i++) {
            ans += Math.abs(students[i] - seats[i]);
        }

        return ans;
    }
}
