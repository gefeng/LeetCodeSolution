package solution.biweekly73;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "All Ancestors of a Node in a Directed Acyclic Graph",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/contest/biweekly-contest-73/problems/all-ancestors-of-a-node-in-a-directed-acyclic-graph/"
)
public class Q2192 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public List<List<Integer>> getAncestors(int n, int[][] edges) {
        List<Set<Integer>> res = new ArrayList<>();
        List<List<Integer>> ans = new ArrayList<>();
        List<Integer>[] g = new List[n];
        int[] ind = new int[n];

        for(int i = 0; i < n; i++) {
            g[i] = new ArrayList<>();
            res.add(new TreeSet<>());
            ans.add(new ArrayList<>());
        }

        for(int[] e : edges) {
            g[e[0]].add(e[1]);
            ind[e[1]]++;
        }

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(ind[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            for(int nei : g[cur]) {
                if(--ind[nei] == 0) {
                    q.offer(nei);
                }

                res.get(nei).addAll(res.get(cur));
                res.get(nei).add(cur);
            }
        }

        for(int i = 0; i < n; i++) {
            ans.get(i).addAll(res.get(i));
        }

        return ans;
    }
}
