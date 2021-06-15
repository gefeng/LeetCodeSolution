package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

@Problem(
        title = "Matchsticks to Square",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/matchsticks-to-square/"
)
public class Q473 {
    public boolean makesquare(int[] matchsticks) {
        return backtrack(matchsticks);
    }

    private boolean backtrack(int[] matchsticks) {
        int n = matchsticks.length;
        int sum = 0;
        for(int ms : matchsticks) {
            sum += ms;
        }

        if(sum % 4 != 0) {
            return false;
        }

        int len = sum / 4;
        int eLeft = 4;
        for(int i = 0; i < n; i++) {
            if(matchsticks[i] == len) {
                matchsticks[i] = 0;
                eLeft--;
            }
        }

        if(n == 4 && eLeft == 0) {
            return true;
        }
        if(eLeft <= 0) {
            return false;
        }

        int[] edges = new int[eLeft];
        Arrays.fill(edges, sum / 4);

        return dfs(matchsticks, edges, 0);
    }

    private boolean dfs(int[] matchsticks, int[] edges, int curr) {
        if(curr == matchsticks.length) {
            return true;
        }

        if(matchsticks[curr] == 0) {
            return dfs(matchsticks, edges, curr + 1);
        }

        Set<Integer> seen = new HashSet<>();
        for(int i = 0; i < edges.length; i++) {
            if(seen.contains(edges[i])) {
                continue;
            }
            if(edges[i] - matchsticks[curr] >= 0) {
                edges[i] -= matchsticks[curr];
                if(dfs(matchsticks, edges, curr + 1)) {
                    return true;
                }
                edges[i] += matchsticks[curr];
            }
            seen.add(edges[i]);
        }

        return false;
    }
}
