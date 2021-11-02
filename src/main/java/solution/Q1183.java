package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Maximum Number of Ones",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/maximum-number-of-ones/"
)
public class Q1183 {
    /**
     * if pick cell (i, j),
     * we can pick all cells whose r % sideLength == i % sideLength and c % sideLength == j % sideLength
     *
     * Iterate all cells within [(0, 0), (sideLength, sideLength)] and count how many cells can be filled based on above conclusion.
     * Keep best results using priority queue.
     *
     * Time:  O(N ^ 2 * log(maxOnes))
     * Space: O(maxOnes)
     * */
    public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
        Queue<Integer> pq = new PriorityQueue<>();
        int ans = 0;
        for(int i = 0; i < sideLength; i++) {
            for(int j = 0; j < sideLength; j++) {
                int cnt = ((width - j + sideLength - 1) / sideLength) * ((height - i + sideLength - 1) / sideLength);
                pq.offer(cnt);

                if(pq.size() > maxOnes) {
                    pq.poll();
                }
            }
        }

        while(!pq.isEmpty()) {
            ans += pq.poll();
        }

        return ans;
    }
}
