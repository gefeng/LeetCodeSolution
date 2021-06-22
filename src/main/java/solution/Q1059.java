package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Problem(
        title = "All Paths from Source Lead to Destination",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/all-paths-from-source-lead-to-destination/"
)
public class Q1059 {
    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        List<Integer>[] graph = new List[n];

        for(int[] e : edges) {
            int u = e[0];
            int v = e[1];
            graph[u] = graph[u] == null ? new ArrayList<>() : graph[u];
            graph[u].add(v);
        }

        return dfs(graph, source, destination, new boolean[n]);
    }

    /*
        termination:
        current node equals to destination and detination's outdegree is 0 then return true
        current node has 0 outdegree but is not equals to destination then return false
        current node equals destination but has positve outdegree.
        find a cycle then return false
    */
    private boolean dfs(List<Integer>[] graph, int source, int destination, boolean[] seen) {
        seen[source] = true;

        List<Integer> neis = graph[source];

        if(neis != null) {
            for(int nei : neis) {
                if(seen[nei] || !dfs(graph, nei, destination, seen)) {
                    return false;
                }
            }
        }

        seen[source] = false;

        if((neis == null && source != destination) || (neis != null && source == destination)) {
            return false;
        }

        return true;
    }
}
