package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Sequence Reconstruction",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/sequence-reconstruction/"
)
public class Q444 {
    /**
     * Time:  O(V)
     * Space: O(V + E)
     * */
    public boolean sequenceReconstruction(int[] org, List<List<Integer>> seqs) {
        if(seqs.size() == 0) {
            return false;
        }
        int n = org.length;
        List<Integer>[] adj = new List[n + 1];
        int[] indegree = new int[n + 1];

        Arrays.fill(indegree, -1);

        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }

        for(List<Integer> seq : seqs) {
            int sz = seq.size();
            if(sz > n) {
                return false;
            }
            if(sz == 1) {
                int u = seq.get(0);
                if(u > n) {
                    return false;
                }
                indegree[u] = indegree[u] == -1 ? 0 : indegree[u];
            }
            for(int i = 0; i < sz - 1; i++) {
                int u = seq.get(i);
                int v = seq.get(i + 1);
                if(u > n || v > n) {
                    return false;
                }
                adj[u].add(v);
                indegree[u] = indegree[u] == -1 ? 0 : indegree[u];
                indegree[v] = indegree[v] == -1 ? 1 : indegree[v] + 1;
            }
        }

        Queue<Integer> queue = new ArrayDeque<>();
        for(int i = 1; i <= n; i++) {
            if(indegree[i] == 0) {
                queue.offer(i);
            }
        }

        int idx = 0;
        while(!queue.isEmpty()) {
            int sz = queue.size();
            if(queue.size() > 1) {
                return false;
            }

            int cur = queue.poll();
            if(cur != org[idx++]) {
                return false;
            }

            for(int nei : adj[cur]) {
                indegree[nei]--;
                if(indegree[nei] == 0) {
                    queue.offer(nei);
                }
            }
        }

        return idx == n;
    }
}
