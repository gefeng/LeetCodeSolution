package solution.weekly289;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.List;

@Problem(
        title = "Longest Path With Different Adjacent Characters",
        difficulty = QDifficulty.HARD,
        tag = QTag.TREE,
        url = "https://leetcode.com/contest/weekly-contest-289/problems/longest-path-with-different-adjacent-characters/"
)
public class Q2246 {
    /**
     * Time:  O(E + V)
     * Space: O(E + V)
     * */
    public int longestPath(int[] parent, String s) {
        int n = parent.length;
        int ans = 0;
        List<Integer>[] g = new List[n];

        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
        }

        for(int i = 0; i < n; i++) {
            if(parent[i] != -1) {
                g[parent[i]].add(i);
            }
        }

        return dfs(g, s, 0)[1];
    }

    private int[] dfs(List<Integer>[] g, String s, int cur) {
        char c = s.charAt(cur);
        int maxGlobal = 1;
        int maxLocal = 1;
        int max1 = 0;
        int max2 = 0;

        for(int children : g[cur]) {
            int[] ret = dfs(g, s, children);
            char cc = s.charAt(children);

            if(cc != c) {
                maxLocal = Math.max(maxLocal, ret[0] + 1);

                if(ret[0] > max1) {
                    max2 = max1;
                    max1 = ret[0];
                } else if(ret[0] > max2) {
                    max2 = ret[0];
                }
            }

            maxGlobal = Math.max(maxGlobal, ret[1]);
        }

        maxGlobal = Math.max(maxGlobal, maxLocal);
        maxGlobal = Math.max(maxGlobal, max1 + max2 + 1);

        return new int[] {maxLocal, maxGlobal};
    }
}
