package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Parallel Courses II",
        difficulty = QDifficulty.HARD,
        tag = QTag.DYNAMIC_PROGRAMMING,
        url = "https://leetcode.com/problems/parallel-courses-ii/"
)
public class Q1494 {
    /**
     * Time:  O(3 ^ N * N)
     * Space: O(N + E)
     * */
    public int minNumberOfSemesters(int n, int[][] relations, int k) {
        List<Integer>[] adj = new List[n];

        for(int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(int[] e : relations) {
            adj[e[1] - 1].add(e[0] - 1);
        }

        return dfs(adj, (1 << n) - 1, n, k, new Integer[1 << n]);
    }

    private int dfs(List<Integer>[] adj, int mask, int n, int k, Integer[] memo) {
        if(mask == 0) {
            return 0;
        }

        if(memo[mask] != null) {
            return memo[mask];
        }

        int courses = 0;
        for(int i = 0; i < n; i++) {
            if(((1 << i) & mask) != 0) {
                boolean canTake = true;
                for(int pre : adj[i]) {
                    if(((1 << pre) & mask) != 0) {
                        canTake = false;
                        break;
                    }
                }

                if(canTake) {
                    courses |= (1 << i);
                }
            }
        }

        int min = Integer.MAX_VALUE;
        for(int i = courses; i > 0; i = (i - 1) & courses) {
            if(Integer.bitCount(i) <= k) {
                min = Math.min(min, dfs(adj, mask - i, n, k, memo) + 1);
            }
        }
        return memo[mask] = min;
    }
}
