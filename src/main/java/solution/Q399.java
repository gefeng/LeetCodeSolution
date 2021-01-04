package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Formatter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

@Problem(
        title = "Evaluate Division",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/evaluate-division/"
)
public class Q399 {
    class Pair {
        String id;
        double weight;
        public Pair(String id, double weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    public double[] calcEquationUnionFind(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, Pair> graph = new HashMap<>();
        double[] ans = new double[queries.size()];
        for(int i = 0; i < equations.size(); i++) {
            union(graph, equations.get(i).get(0), equations.get(i).get(1), values[i]);
        }

        for(int i = 0; i < queries.size(); i++) {
            String dividend = queries.get(i).get(0);
            String divisor = queries.get(i).get(1);
            if(!graph.containsKey(dividend) || !graph.containsKey(divisor)) {
                ans[i] = -1.0;
            }
            else {
                Pair p1 = find(graph, dividend);
                Pair p2 = find(graph, divisor);
                if(!p1.id.equals(p2.id))
                    ans[i] = -1.0;
                else
                    ans[i] = p1.weight / p2.weight;
            }
        }

        return ans;
    }

    private Pair find(HashMap<String, Pair> graph, String id) {
        if(!graph.containsKey(id)) {
            graph.put(id, new Pair(id, 1.0));
        }

        Pair entry = graph.get(id);
        if(!entry.id.equals(id)) {
            Pair newEntry = find(graph, entry.id);
            graph.put(id, new Pair(newEntry.id, newEntry.weight * entry.weight));
        }

        return graph.get(id);
    }

    private void union(HashMap<String, Pair> graph, String dividend, String divisor, double quotient) {
        Pair x = find(graph, dividend);
        Pair y = find(graph, divisor);
        if(!x.id.equals(y.id)) {
            graph.put(x.id, new Pair(y.id, quotient * y.weight / x.weight));
        }
    }


    public double[] calcEquationDFS(List<List<String>> equations, double[] values, List<List<String>> queries) {
        double[] ans = new double[queries.size()];
        HashMap<String, HashMap<String, Double>> graph = new HashMap<>();

        for(int i = 0; i < equations.size(); i++) {
            String dividend = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);
            double quotient = values[i];

            if(!graph.containsKey(dividend)) {
                graph.put(dividend, new HashMap<>());
            }
            if(!graph.containsKey(divisor)) {
                graph.put(divisor, new HashMap<>());
            }

            graph.get(dividend).put(divisor, quotient);
            graph.get(divisor).put(dividend, 1 / quotient);
        }

        for(int i = 0; i < queries.size(); i++) {
            String dividend = queries.get(i).get(0);
            String divisor = queries.get(i).get(1);
            if(!graph.containsKey(dividend) || !graph.containsKey(divisor))
                ans[i] = -1.0;
            else if(dividend == divisor)
                ans[i] = 1.0;
            else {
                ans[i] = backTrack(graph, dividend, divisor, 1.0, new HashSet<>());
            }
        }

        return ans;
    }

    private double backTrack(HashMap<String, HashMap<String, Double>> graph, String source, String target, double product, HashSet<String> visited) {
        if(graph.get(source).containsKey(target)) {
            return product * graph.get(source).get(target);
        }

        visited.add(source);

        for(String key : graph.get(source).keySet()) {
            if(!visited.contains(key)) {
                double ret = backTrack(graph, key, target, product * graph.get(source).get(key), visited);
                if(ret != -1.0)
                    return ret;
            }
        }

        visited.remove(source);
        return -1.0;
    }
}
