package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

@Problem(
        title = "Exclusive Time of Functions",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.STACK,
        url = "https://leetcode.com/problems/exclusive-time-of-functions/"
)
public class Q636 {
    public int[] exclusiveTime(int n, List<String> logs) {
        int[] excTime = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();

        int prev = 0;
        for(String log : logs) {
            String[] items = log.split(":");
            int[] currCall = new int[] {
                    Integer.parseInt(items[0]),
                    Integer.parseInt(items[2])
            };

            if(items[1].equals("start")) {
                if(!stack.isEmpty()) {
                    excTime[stack.peek()] += (currCall[1] - prev);
                }
                stack.push(currCall[0]);
                prev = currCall[1];
            } else {
                excTime[stack.pop()] += (currCall[1] - prev + 1);
                prev = currCall[1] + 1;
            }
        }

        return excTime;
    }
}
