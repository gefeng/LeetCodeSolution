package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Minimum Initial Energy to Finish Tasks",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-initial-energy-to-finish-tasks/"
)
public class Q1665 {
    /**
     * Sort by (minimum - actual)
     * if there is no sufficient energy to start task i, we borrow task[i][1] - curr_energy
     *
     * Time:  O(NlogN)
     * Space: O(logN)
     * */
    public int minimumEffort(int[][] tasks) {
        int total = 0;
        int energy = 0;

        Arrays.sort(tasks, (a, b) -> Integer.compare(b[1] - b[0], a[1] - a[0]));

        for(int[] t : tasks) {
            if(energy < t[1]) {
                total += t[1] - energy;
                energy = t[1];
            }
            energy -= t[0];
        }

        return total;
    }
}
