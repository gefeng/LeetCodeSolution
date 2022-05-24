package solution.weekly294;

import java.util.*;
import annotations.*;
import enums.*;

@Problem(
        title = "Maximum Bags With Full Capacity of Rocks",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/weekly-contest-294/problems/maximum-bags-with-full-capacity-of-rocks/"
)
public class Q2279 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int n = capacity.length;

        int[] A = new int[n];

        for(int i = 0; i < n; i++) {
            A[i] = capacity[i] - rocks[i];
        }

        Arrays.sort(A);

        int cnt = 0;
        for(int i = 0; i < n; i++) {
            int rem = Math.min(additionalRocks, A[i]);
            A[i] -= rem;
            additionalRocks -= rem;
            if(A[i] == 0) {
                cnt++;
            }
        }

        return cnt;
    }
}
