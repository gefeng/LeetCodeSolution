package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.PriorityQueue;

@Problem(
        title = "Course Schedule III",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/course-schedule-iii/"
)
public class Q630 {
    public int scheduleCourse(int[][] courses) {
        //return greedySolution(courses);
        return dpSolution(courses);
    }

    /*
        Can we apply dp? Since dp is more preferrable than greedy
        Is this a variant 0/1 knapsack problem?
        bag capacity: max deadline
        item weight: duration
        return maximum number of courses you can put on your schedule (bag) :)

        state:
            dp[i][j] means max number of courses you can take till ith courses before time j
        transition:
            dp[i][j] = max(dp[i - 1][j], dp[i - 1][j - curr_duration] + 1)
    */
    private int dpSolution(int[][] courses) {
        Arrays.sort(courses, (a, b) -> Integer.compare(a[1], b[1]));
        int m = courses.length;
        int n = 0;
        for(int[] course : courses) {
            n = Math.max(n, course[1]);
        }

        int[][] dp = new int[m + 1][n + 1];
        for(int i = 1; i < m + 1; i++) {
            for(int j = 1; j < n + 1; j++) {
                dp[i][j] = Math.max(dp[i][j], Math.max(dp[i - 1][j], dp[i][j - 1]));
                int duration = courses[i - 1][0];
                int deadline = courses[i - 1][1];
                if(j - duration >= 0 && j <= deadline) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - duration] + 1);
                }
            }
        }

        return dp[m][n];
    }

    /*
        1. Sort course by deadline, we need to check more urgent course first.
        2. Employ max heap to save duration of picked courses. Why?
           Courses in heap are those courses completed within current time frame.
           So there are two cases:
           a. new course can be completed before deadline. Just simply add to heap as a candidate.
           b. new course cannot be completed before deadline. In this case, compare the course's duration
              with the maximum duration on heap. If current course's duration is less, replace the max on
              heap with current duration. Why is this true? because current course has a less strict deadline
              and short duration which is optimal than the one with more strict dealine and longer duration.
    */
    private int greedySolution(int[][] courses) {
        Arrays.sort(courses, (a, b) -> Integer.compare(a[1], b[1]));

        int days = 0;
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        for(int[] course : courses) {
            int duration = course[0];
            int deadline = course[1];

            if(duration + days > deadline) {
                if(!maxHeap.isEmpty() && maxHeap.peek() > duration) {
                    days -= (maxHeap.poll() - duration);
                    maxHeap.offer(duration);
                }
            } else {
                maxHeap.offer(duration);
                days +=  duration;
            }
        }

        return maxHeap.size();
    }
}
