package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;

@Problem(
        title = "Sort Items by Groups Respecting Dependencies",
        difficulty = QDifficulty.HARD,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/sort-items-by-groups-respecting-dependencies/"
)
public class Q1203 {
    /**
     * Time:  O(N ^ 2)
     * Space: O(N ^ 2)
     * */
    public int[] sortItems(int n, int m, int[] group, List<List<Integer>> beforeItems) {
        int tot = 0;
        for(int x : group) {
            if(x == -1) {
                tot++;
            }
        }

        tot += m;
        List<Integer>[] g = new List[tot];
        int idx = m; // generate group id for non-group items
        for(int i = 0; i < tot; i++) {
            g[i] = new ArrayList<>();
        }
        for(int i = 0; i < n; i++) {
            if(group[i] == -1) {
                g[idx++].add(i);
                group[i] = idx - 1;
            } else {
                g[group[i]].add(i);
            }
        }

        List<Integer>[] adjG = new List[tot];
        List<Integer>[] adjI = new List[n];
        int[] indG = new int[tot];
        int[] indI = new int[n];

        for(int i = 0; i < n; i++) {
            adjI[i] = new ArrayList<>();
        }
        for(int i = 0; i < tot; i++) {
            adjG[i] = new ArrayList<>();
        }


        for(int i = 0; i < n; i++) {
            int gu = group[i];
            List<Integer> dep = beforeItems.get(i);

            for(int d : dep) {
                int gv = group[d];
                if(gv == gu) {
                    adjI[d].add(i);
                    indI[i]++;
                } else {
                    adjG[gv].add(gu);
                    indG[gu]++;
                }
            }
        }

        for(int i = 0; i < m; i++) {
            List<Integer> s = topSort1(g[i], adjI, indI);
            if(s.size() != g[i].size()) {
                return new int[0];
            }
            g[i] = s;
        }

        List<Integer> s = topSort2(g, adjG, indG);
        if(s.size() != n) {
            return new int[0];
        }
        return s.stream().mapToInt(a -> a.intValue()).toArray();
    }

    private List<Integer> topSort1(List<Integer> l, List<Integer>[] adj, int[] ind) {
        List<Integer> res = new ArrayList<>();
        Queue<Integer> q = new ArrayDeque<>();
        for(int x : l) {
            if(ind[x] == 0) {
                q.offer(x);
            }
        }
        while(!q.isEmpty()) {
            int cur = q.poll();
            res.add(cur);

            for(int nei : adj[cur]) {
                ind[nei]--;
                if(ind[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        return res;
    }

    private List<Integer> topSort2(List<Integer>[] g, List<Integer>[] adj, int[] ind) {
        List<Integer> res = new ArrayList<>();
        int n = g.length;

        Queue<Integer> q = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(ind[i] == 0) {
                q.offer(i);
            }
        }

        while(!q.isEmpty()) {
            int cur = q.poll();

            res.addAll(g[cur]);

            for(int nei : adj[cur]) {
                ind[nei]--;
                if(ind[nei] == 0) {
                    q.offer(nei);
                }
            }
        }

        return res;
    }
}
