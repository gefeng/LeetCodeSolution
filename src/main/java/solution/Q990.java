package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Problem(
        title = "Satisfiability of Equality Equations",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.UNION_FIND,
        url = "https://leetcode.com/problems/satisfiability-of-equality-equations/"
)
public class Q990 {
    public boolean equationsPossible(String[] equations) {
        return dfsSolution(equations);
    }

    private boolean dfsSolution(String[] equations) {
        HashMap<Character, List<Character>> graph = new HashMap<>();
        for(String s : equations) {
            if(s.charAt(1) == '!')
                continue;
            char x = s.charAt(0);
            char y = s.charAt(3);
            if(!graph.containsKey(x))
                graph.put(x, new ArrayList<>());
            if(!graph.containsKey(y))
                graph.put(y, new ArrayList<>());
            graph.get(x).add(y);
            graph.get(y).add(x);
        }

        for(String s : equations) {
            if(s.charAt(1) == '=')
                continue;
            char x = s.charAt(0);
            char y = s.charAt(3);
            if(x == y)
                return false;
            if(graph.containsKey(x) && graph.containsKey(y)) {
                if(isConnected(graph, x, y, new boolean[26]))
                    return false;
            }
        }

        return true;
    }

    private boolean isConnected(HashMap<Character, List<Character>> graph, char target, char v, boolean[] visited) {
        if(target == v)
            return true;
        visited[v - 'a'] = true;
        for(char neighbor : graph.get(v)) {
            if(!visited[neighbor - 'a']) {
                if(isConnected(graph, target, neighbor, visited))
                    return true;
            }
        }
        return false;
    }

    private boolean unionFindSolution(String[] equations) {
        int[] parent = new int[26];
        for(int i = 0; i < parent.length; i++)
            parent[i] = i;

        for(String s : equations) {
            if(s.charAt(1) == '!')
                continue;

            char x = s.charAt(0);
            char y = s.charAt(1);

            int groupA = find(parent, x - 'a');
            int groupB = find(parent, y - 'a');
            if(groupA != groupB)
                parent[groupA] = groupB;
        }

        for(String s : equations) {
            if(s.charAt(1) == '=')
                continue;

            char x = s.charAt(0);
            char y = s.charAt(1);

            if(x == y)
                return false;
            int groupA = find(parent, x - 'a');
            int groupB = find(parent, y - 'a');
            if(groupA == groupB)
                return false;
        }

        return true;
    }

    private int find(int[] parent, int i) {
        if(parent[i] != i)
            parent[i] = find(parent, parent[i]);
        return parent[i];
    }
}
