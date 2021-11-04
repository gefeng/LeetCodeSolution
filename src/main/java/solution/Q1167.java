package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;

@Problem(
        title = "Minimum Cost to Connect Sticks",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/minimum-cost-to-connect-sticks/"
)
public class Q1167 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int connectSticks(int[] sticks) {
        int cost = 0;
        PriorityQueue<Integer> pQueue = new PriorityQueue<>();
        for(int stick : sticks)
            pQueue.offer(stick);

        while(pQueue.size() > 1) {
            int x = pQueue.poll();
            int y = pQueue.poll();
            cost += (x + y);
            pQueue.offer(x + y);
        }

        return cost;
    }
}
