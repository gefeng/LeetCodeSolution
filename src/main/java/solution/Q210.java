package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Course schedule II",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.TOPOLOGICAL_SORT,
        url = "https://leetcode.com/problems/course-schedule-ii/"
)
public class Q210 {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();
        int[] courses = new int[numCourses];
        int[] degree = new int[numCourses];

        // O(m)
        for(int[] pq : prerequisites) {
            if(!graph.containsKey(pq[1]))
                graph.put(pq[1], new ArrayList<>());
            graph.get(pq[1]).add(pq[0]);
            degree[pq[0]]++;
        }

        // O(n)
        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < degree.length; i++) {
            if(degree[i] == 0)
                queue.offer(i);
        }

        // O(m + n)
        int index = 0;
        while(!queue.isEmpty()) {
            int preCourse = queue.poll();
            courses[index++] = preCourse;

            if(graph.containsKey(preCourse)) {
                for(int course : graph.get(preCourse)) {
                    degree[course]--;
                    if(degree[course] == 0)
                        queue.offer(course);
                }
            }
        }

        if(index != numCourses)
            return new int[0];
        return courses;
    }
}
