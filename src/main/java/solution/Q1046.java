package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Last Weight Stone",
        difficulty = QDifficulty.EASY,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/last-stone-weight/"
)
public class Q1046 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int lastStoneWeight(int[] stones) {
        int ans = 0;
        int n = stones.length;
        Queue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());

        for(int s : stones) {
            pq.offer(s);
        }

        while(pq.size() > 1) {
            int fst = pq.poll();
            int sec = pq.poll();

            int diff = Math.abs(fst - sec);

            if(diff > 0) {
                pq.offer(diff);
            }
        }

        return pq.isEmpty() ? 0 : pq.peek();
    }
}
