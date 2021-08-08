package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Remove Stones to Minimize the Total",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/remove-stones-to-minimize-the-total/"
)
public class Q1962 {
    /**
     * Time:  O(K * logN)
     * Space: O(N)
     * */
    public int minStoneSum(int[] piles, int k) {
        int res = 0;
        Queue<Integer> maxHeap = new PriorityQueue<>(Comparator.reverseOrder());

        for(int p : piles) {
            maxHeap.offer(p);
            res += p;
        }

        while(k > 0 && !maxHeap.isEmpty()) {
            int p = maxHeap.poll();

            res -= p / 2;
            p -= p / 2;

            maxHeap.offer(p);
            k--;
        }

        return res;
    }
}
