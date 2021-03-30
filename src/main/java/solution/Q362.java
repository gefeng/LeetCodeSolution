package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

@Problem(
        title = "Design Hit Counter",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.DESIGN,
        url = "https://leetcode.com/problems/design-hit-counter/"
)
public class Q362 {
    /*
    * This approach is scalable with huge amount hits per second.
    * Since we only save the same timestamp once and using a map
    * to count the hits for that timestamp
    * */
    private HashMap<Integer, Integer> timeToHit;
    private Queue<Integer> window;
    private int count;
    private int prevTimestamp;
    public Q362() {
        timeToHit = new HashMap<>();
        window = new LinkedList<>();
        count = 0;
        prevTimestamp = 0;
    }

    /** Record a hit.
     @param timestamp - The current timestamp (in seconds granularity). */
    public void hit(int timestamp) {
        count++;

        timeToHit.put(timestamp, timeToHit.getOrDefault(timestamp, 0) + 1);
        if(window.isEmpty() || prevTimestamp != timestamp)
            window.offer(timestamp);

        prevTimestamp = timestamp;
    }

    /** Return the number of hits in the past 5 minutes.
     @param timestamp - The current timestamp (in seconds granularity). */
    public int getHits(int timestamp) {
        while(!window.isEmpty() && window.peek() + 300 <= timestamp) {
            int expired = window.poll();
            count -= timeToHit.get(expired);
            timeToHit.remove(expired);
        }

        return count;
    }
}
