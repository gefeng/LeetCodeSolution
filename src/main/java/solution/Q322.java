package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Reconstruct Itinerary",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GRAPH,
        url = "https://leetcode.com/problems/reconstruct-itinerary/"
)
public class Q322 {
    public List<String> findItinerary(List<List<String>> tickets) {
        HashMap<String, List<String>> graph = new HashMap<>();
        buildGraph(tickets, graph);

        List<String> route = new LinkedList<>();
        dfs(graph, "JFK", route);

        return route;
    }

    private void buildGraph(List<List<String>> tickets, HashMap<String, List<String>> graph) {
        for(List<String> ticket : tickets) {
            String dep = ticket.get(0);
            String arr = ticket.get(1);
            if(!graph.containsKey(dep))
                graph.put(dep, new LinkedList<>());
            graph.get(dep).add(arr);
        }

        for(String key : graph.keySet())
            Collections.sort(graph.get(key));
    }

    private void dfs(HashMap<String, List<String>> graph, String airport, List<String> route) {
        if(graph.containsKey(airport)) {
            List<String> neighbors = graph.get(airport);
            while(!neighbors.isEmpty())
                dfs(graph, neighbors.remove(0), route);
        }
        route.add(0, airport);
    }
}
