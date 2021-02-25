package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Course Schedule",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/course-schedule/"
)
public class Q207 {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return topSortSolution(numCourses, prerequisites);
    }

    private boolean dfsSolution(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        for(int[] pq : prerequisites) {
            if(!graph.containsKey(pq[0]))
                graph.put(pq[0], new ArrayList<>());
            graph.get(pq[0]).add(pq[1]);
        }

        boolean[] checked = new boolean[numCourses];
        boolean[] visited = new boolean[numCourses];
        for(int i = 0; i < numCourses; i++) {
            if(findCycle(graph, i, visited, checked))
                return false;
        }

        return true;
    }

    private boolean findCycle(HashMap<Integer, List<Integer>> graph, int v, boolean[] visited, boolean[] checked) {
        if(checked[v])
            return false;

        checked[v] = true;
        visited[v] = true;

        boolean hasCycle = false;

        if(graph.containsKey(v)) {
            for(int neighbor : graph.get(v)) {
                if(visited[neighbor] || findCycle(graph, neighbor, visited, checked)) {
                    hasCycle = true;
                    break;
                }
            }
        }

        visited[v] = false;

        return hasCycle;
    }

    private boolean topSortSolution(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        int[] degree = new int[numCourses];

        for(int[] pq : prerequisites) {
            if(!graph.containsKey(pq[1]))
                graph.put(pq[1], new ArrayList<>());
            graph.get(pq[1]).add(pq[0]);
            degree[pq[0]]++;
        }

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < numCourses; i++) {
            if(degree[i] == 0)
                queue.offer(i);
        }

        int removedCourses = 0;
        while(!queue.isEmpty()) {
            int pre = queue.poll();
            removedCourses++;
            if(!graph.containsKey(pre))
                continue;

            for(int course : graph.get(pre)) {
                degree[course]--;
                if(degree[course] == 0)
                    queue.offer(course);
            }
        }

        return removedCourses == numCourses;
    }
}
