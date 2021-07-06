package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Number Of Ways To Reconstruct A Tree",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/number-of-ways-to-reconstruct-a-tree/"
)
public class Q1719 {
    /*
    * 这题太难对于我来说，照着大佬思路implement了一下.
    * */
    public int checkWays(int[][] pairs) {
        Map<Integer, Set<Integer>> adj = new HashMap<>();
        Set<Integer> nodes = new HashSet<>();
        for(int[] e : pairs) {
            int u = e[0];
            int v = e[1];
            Set<Integer> n1 = adj.computeIfAbsent(u, k -> new HashSet<>());
            Set<Integer> n2 = adj.computeIfAbsent(v, k -> new HashSet<>());
            n1.add(v);
            n2.add(u);
            nodes.add(u);
            nodes.add(v);
        }

        return dfs(adj, nodes);
    }

    private int dfs(Map<Integer, Set<Integer>> adj, Set<Integer> nodes) {
        int n = nodes.size();

        List<Integer> roots = new ArrayList<>();
        for(int node : nodes) {
            if(adj.get(node).size() == n - 1) {
                roots.add(node);
            }
        }

        if(roots.size() == 0) {
            return 0;
        }

        // multiple roots can be swapped, so only need to test one of the roots
        int root = roots.get(0);

        // disconnect children from roots
        for(int nei : adj.get(root)) {
            adj.get(nei).remove(root);
        }

        List<Set<Integer>> comps = new ArrayList<>();
        Set<Integer> seen = new HashSet<>();
        for(int node : nodes) {
            if(node != root && !seen.contains(node)) {
                Set<Integer> comp = new HashSet<>();
                findComps(adj, comp, seen, node);
                comps.add(comp);
            }
        }

        int ret = 1;
        for(Set<Integer> comp : comps) {
            int res = dfs(adj, comp);
            if(res == 0) {
                ret = 0;
                break;
            }
            if(res == 2) {
                ret = 2;
            }
        }

        if(ret == 1 && roots.size() > 1) {
            ret = 2;
        }

        return ret;
    }

    private void findComps(Map<Integer, Set<Integer>> adj, Set<Integer> comp, Set<Integer> seen, int node) {
        seen.add(node);
        comp.add(node);

        for(int nei : adj.get(node)) {
            if(seen.contains(nei)) {
                continue;
            }

            findComps(adj, comp, seen, nei);
        }
    }
}
