package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

@Problem(
        title = "Time Needed to Buy Tickets",
        difficulty = QDifficulty.EASY,
        tag = QTag.MATH,
        url = "https://leetcode.com/problems/time-needed-to-buy-tickets/"
)
public class Q2073 {
    /**
     * Simulation is trivial, we can do it in O(N) time with O(1) space.
     * Each of the person from [0, k] will buy a total of min(tickets[k], tickets[i])
     * Each of the person after k gonna buy all the tickets they want if tickets[i] < ticket[k], otherwise tickets[k] - 1 (last round doesn't count)
     *
     * Time:  O(N)
     * Space: O(1)
     * */
    public int timeRequiredToBuy(int[] tickets, int k) {
        int ans = 0;
        int n = tickets.length;

        for(int i = 0; i < n; i++) {
            if(i <= k) {
                ans += Math.min(tickets[k], tickets[i]);
            } else {
                ans += tickets[k] > tickets[i] ? tickets[i] : tickets[k] - 1;
            }
        }

        return ans;
    }
}
