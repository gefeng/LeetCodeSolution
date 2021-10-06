package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Sort Integers by The Power Value",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.SORT,
        url = "https://leetcode.com/problems/sort-integers-by-the-power-value/"
)
public class Q1387 {
    /**
     * Time:  O(N * (M + logK))
     * Space: O(K)
     * */
    public int getKth(int lo, int hi, int k) {
        Queue<int[]> maxHeap = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) {
                return Integer.compare(b[0], a[0]);
            }
            return Integer.compare(b[1], a[1]);
        });

        for(int i = lo; i <= hi; i++) {
            int p = getPower(i);

            maxHeap.offer(new int[] {i, p});
            if(maxHeap.size() > k) {
                maxHeap.poll();
            }
        }

        return maxHeap.poll()[0];
    }

    private int getPower(int x) {
        int p = 0;
        while(x != 1) {
            if(x % 2 == 0) {
                x >>= 1;
            } else {
                x = 3 * x + 1;
            }
            p++;
        }
        return p;
    }
}
