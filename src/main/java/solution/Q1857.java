package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Largest Color Value in a Directed Graph",
        difficulty = QDifficulty.HARD,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/largest-color-value-in-a-directed-graph/"
)
public class Q1857 {
    /*
    * 有向图，有环可以用topological sort
    * */
    public int largestPathValue(String colors, int[][] edges) {
        return dfsMemo(colors, edges);
    }

    private int dfsMemo(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] graph = new List[n];

        for(int[] edge : edges) {
            int v1 = edge[0];
            int v2 = edge[1];
            if(graph[v1] == null) {
                graph[v1] = new ArrayList<>();
            }
            graph[v1].add(v2);
        }

        int maxColorValue = 0;
        Set<Integer> visited = new HashSet<>();
        Map<Integer, int[]> pathColorMap = new HashMap<>();
        for(int i = 0; i < n; i++) {
            if(visited.contains(i)) {
                continue;
            }

            Set<Integer> cycleSet = new HashSet<>();
            if(dfs(graph, colors, visited, cycleSet, pathColorMap, i) == null) {
                return -1;
            }
        }

        for(int key : pathColorMap.keySet()) {
            int[] pathColor = pathColorMap.get(key);
            for(int i = 0; i < pathColor.length; i++) {
                maxColorValue = Math.max(pathColor[i], maxColorValue);
            }
        }

        return maxColorValue;
    }

    private int[] dfs(List<Integer>[] graph, String colors, Set<Integer> visited, Set<Integer> cycleSet,
                      Map<Integer, int[]> pathColorMap, int currNode) {
        visited.add(currNode);
        cycleSet.add(currNode);

        int[] pathColor = new int[26];
        if(graph[currNode] != null) {
            for(int nei : graph[currNode]) {
                if(cycleSet.contains(nei)) {
                    return null;
                }
                int[] subpathColor = null;
                if(!visited.contains(nei)) {
                    subpathColor = dfs(graph, colors, visited, cycleSet, pathColorMap, nei);
                    if(subpathColor == null) {
                        return null;
                    }
                } else {
                    subpathColor = pathColorMap.get(nei);
                }

                for(int i = 0; i < subpathColor.length; i++) {
                    pathColor[i] = Math.max(pathColor[i], subpathColor[i]);
                }
            }
        }

        pathColor[colors.charAt(currNode) - 'a']++;

        pathColorMap.put(currNode, pathColor);

        cycleSet.remove(currNode); // cycle detection
        return pathColor;
    }



    private class Node {
        int id;
        int[] countColor;
        Node(int id) {
            this.id = id;
            this.countColor = new int[26];
        }
    }
    public int topologicalSolution(String colors, int[][] edges) {
        int n = colors.length();
        List<Integer>[] outdegree = new List[n];
        int[] indegree = new int[n];
        Node[] nodes = new Node[n];

        for(int i = 0; i < n; i++) {
            nodes[i] = new Node(i);
        }

        for(int[] e : edges) {
            if(outdegree[e[0]] == null) {
                outdegree[e[0]] = new ArrayList<>();
            }
            outdegree[e[0]].add(e[1]);
            indegree[e[1]]++;
        }

        Queue<Node> queue = new ArrayDeque<>();
        for(int i = 0; i < n; i++) {
            if(indegree[i] == 0) {
                queue.offer(nodes[i]);
            }
        }

        int maxColor = 0;
        int countProcessedNode = 0;
        while(!queue.isEmpty()) {
            Node curr = queue.poll();
            countProcessedNode++;

            curr.countColor[colors.charAt(curr.id) - 'a']++;
            for(int i = 0; i < curr.countColor.length; i++) {
                maxColor = Math.max(maxColor, curr.countColor[i]);
            }

            List<Integer> neighbors = outdegree[curr.id];
            if(neighbors == null) {
                continue;
            }

            for(int nei : neighbors) {
                Node neiNode = nodes[nei];
                for(int i = 0; i < curr.countColor.length; i++) {
                    neiNode.countColor[i] = Math.max(neiNode.countColor[i], curr.countColor[i]);
                }

                indegree[nei]--;
                if(indegree[nei] == 0) {
                    queue.offer(neiNode);
                }
            }
        }

        if(countProcessedNode != n) {
            return -1;
        }

        return maxColor;
    }
}
