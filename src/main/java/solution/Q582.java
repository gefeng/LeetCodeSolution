package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Problem(
        title = "Kill Process",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DEPTH_FIRST_SEARCH,
        url = "https://leetcode.com/problems/kill-process/"
)
public class Q582 {
    /**
     * Time:  O(V + E)
     * Space: O(V + E)
     * */
    public List<Integer> killProcess(List<Integer> pid, List<Integer> ppid, int kill) {
        int n = pid.size();
        int root = 0;
        Map<Integer, List<Integer>> adj = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int p = pid.get(i);
            int pp = ppid.get(i);

            if(pp == 0) {
                root = p;
            } else {
                adj.computeIfAbsent(pp, k -> new ArrayList<>()).add(p);
            }
        }

        List<Integer> res = new ArrayList<>();
        dfs(adj, kill, res);
        return res;
    }

    private void dfs(Map<Integer, List<Integer>> adj, int p, List<Integer> res) {
        res.add(p);

        List<Integer> children = adj.get(p);
        if(children != null) {
            for(int child : children) {
                dfs(adj, child, res);
            }
        }
    }
}
