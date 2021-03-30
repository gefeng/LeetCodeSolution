package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.PriorityQueue;

@Problem(
        title = "Task Scheduler",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/task-scheduler/"
)
public class Q621 {
    private class Task {
        char id;
        int totalLeft;
        int availableTime;
        Task(char id, int totalLeft, int availableTime) {
            this.id = id;
            this.totalLeft = totalLeft;
            this.availableTime = availableTime;
        }
    }

    public int leastInterval(char[] tasks, int n) {
        return maxFreqSolution(tasks, n);
    }

    private int heapSolution(char[] tasks, int n) {
        int currTime = 0;
        HashMap<Character, Integer> taskToCount = new HashMap<>();
        PriorityQueue<Task> cdQueue = new PriorityQueue<>((a, b) -> (a.availableTime - b.availableTime));
        PriorityQueue<Task> wkQueue = new PriorityQueue<>((a, b) -> (b.totalLeft - a.totalLeft));

        for(char t : tasks)
            taskToCount.put(t, taskToCount.getOrDefault(t, 0) + 1);

        for(char key : taskToCount.keySet()) {
            int left = taskToCount.get(key);
            wkQueue.offer(new Task(key, left, 0));
            taskToCount.put(key, left - 1);
        }

        while(!wkQueue.isEmpty() || !cdQueue.isEmpty()) {
            // check if any tasks can be moved from cdQueue -> wkQueue
            while(!cdQueue.isEmpty() && currTime >= cdQueue.peek().availableTime) {
                wkQueue.offer(cdQueue.poll());
            }

            // process one task from wkQueue
            if(!wkQueue.isEmpty()) {
                Task t = wkQueue.poll();
                int left = taskToCount.get(t.id);
                if(left != 0) {
                    cdQueue.offer(new Task(t.id, left, currTime + 1 + n));
                    taskToCount.put(t.id, left - 1);
                }
            }

            currTime++;
        }

        return currTime;
    }

    /*
    * The solution is based on the fact that the maximum idol time is defined by the most frequent task.
    * WHY?
    * There are two situations
    * Lets say we have 5 A tasks and it's the most frequent task. 2 is the cooldown. idle time A task is (5 - 1) * 2 = 8
    * We can reuse these 8 time slots to process other tasks
    * 1. the rest of the tasks cannot fill all the 8 time slots
    * i.e. A B C A B . A .. A .. A
    * It's obvious the maximum idol time is driven by A's idol time
    * 2. 8 time slots are not enough for the rest of the tasks
    * i.e. A B C A B C A B C A B C A  what if we have 3 D tasks left?
    * we can insert as many different type of tasks as possible between A
    * i.e. A B C D A B C D A B C D A B C A
    * Note we can only insert one task per type in one A's idol window. This guarantee each task is processed out of it's cooldown.
    * i.e A B B A is incorrect
    * */
    private int maxFreqSolution(char[] tasks, int n) {
        int time = 0;
        int maxIdle = 0;
        int maxFreq = 0;
        int maxFreqTask = 0;
        int[] count = new int[26];
        for(char t : tasks)
            count[t - 'A']++;

        for(int i = 0; i < count.length; i++) {
            if(maxFreq < count[i]) {
                maxFreq = count[i];
                maxFreqTask = i;
            }
        }
        maxIdle = (maxFreq - 1) * n;
        time += maxFreq;
        count[maxFreqTask] = 0;

        for(int i = 0; i < count.length; i++) {
            time += count[i];
            maxIdle = Math.max(0, maxIdle - Math.min(maxFreq - 1, count[i]));
        }

        time += maxIdle;
        return time;
    }
}
