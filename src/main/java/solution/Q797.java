package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Problem(
        title = "All Paths From Source to Target",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.BACKTRACKING,
        url = "https://leetcode.com/problems/all-paths-from-source-to-target/"
)
public class Q797 {
    /**
     * total number of path = sum of 1Cn-2 + 2Cn-2 + ... kCn-2 = 2 ^ N
     * where k is the length of the path excluding the start and end.
     *
     * Time:  O(2 ^ N * N)
     * Space: O(2 ^ N * N)
     * */
    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        int n = graph.length;

        return dfs(graph, 0);
    }

    private List<List<Integer>> dfs(int[][] g, int cur) {
        List<List<Integer>> ret = new ArrayList<>();
        int n = g.length;

        if(cur == n - 1) {
            ret.add(Arrays.asList(cur));
            return ret;
        }

        for(int nei : g[cur]) {
            List<List<Integer>> path = dfs(g, nei);

            for(List<Integer> p : path) {
                List<Integer> np = new ArrayList<>();
                np.add(cur);
                np.addAll(p);
                ret.add(np);
            }
        }

        return ret;
    }
}