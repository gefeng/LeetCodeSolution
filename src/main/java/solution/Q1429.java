package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "First Unique Number",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/first-unique-number/"
)
public class Q1429 {
    private Queue<Integer> queue;
    private HashMap<Integer, Boolean> uniqueMap;
    public Q1429(int[] nums) {
        queue = new LinkedList<>();
        uniqueMap = new HashMap<>();
        for(int n : nums)
            add(n);
    }

    public int showFirstUnique() {
        return queue.isEmpty() ? -1 : queue.peek();
    }

    public void add(int value) {
        if(uniqueMap.containsKey(value))
            uniqueMap.put(value, false);
        else {
            uniqueMap.put(value, true);
            queue.offer(value);
        }

        while(!queue.isEmpty() && !uniqueMap.get(queue.peek()))
            queue.poll();
    }
}
