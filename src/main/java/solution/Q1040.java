package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;

@Problem(
        title = "Moving Stones Until Consecutive II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TWO_POINTERS,
        url = "https://leetcode.com/problems/moving-stones-until-consecutive-ii/"
)
public class Q1040 {
    /**
     * Time:  O(N * logN)
     * Space: O(logN)
     * */
    public int[] numMovesStonesII(int[] stones) {
        int n = stones.length;
        int min = n;
        int max = 0;

        Arrays.sort(stones);

        int l = 0;
        int r = 0;
        while(l < n && r < n) {
            while(r + 1 < n && stones[r + 1] - stones[l] + 1 <= n) {
                r++;
            }

            int rem = n - (r - l + 1);
            int steps = rem;

            if(r - l + 1 == n - 1 && stones[r] - stones[l] == r - l) {
                steps += 1;
            }

            min = Math.min(min, steps);

            l++;
        }

        max = Math.max(stones[n - 1] - stones[1] + 1 - (n - 1), stones[n - 2] - stones[0] + 1 - (n - 1));

        return new int[] {min, max};
    }
}
