package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Reconstruct Itinerary",
        difficulty = QDifficulty.HARD,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/reconstruct-itinerary/"
)
public class Q332 {
    /**
     * Eulerian Path
     *
     * Time:  O(E * logE)
     * Space: O(V + E)
     * */
    public List<String> findItinerary(List<List<String>> tickets) {
        List<String> path = new ArrayList<>();
        Map<String, Queue<String>> g = new HashMap<>();

        for(List<String> t : tickets) {
            String u = t.get(0);
            String v = t.get(1);

            g.computeIfAbsent(u, k -> new PriorityQueue<>()).add(v);
        }

        dfs(g, "JFK", path);
        path.add("JFK");
        Collections.reverse(path);

        return path;
    }

    private void dfs(Map<String, Queue<String>> g, String cur, List<String> path) {
        Queue<String> pq = g.get(cur);

        if(pq != null) {
            while(!pq.isEmpty()) {
                String nei = pq.poll();
                dfs(g, nei, path);
                path.add(nei);
            }
        }
    }
}
