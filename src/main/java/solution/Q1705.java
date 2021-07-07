package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Maximum Number of Eaten Apples",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/maximum-number-of-eaten-apples/"
)
public class Q1705 {
    public int eatenApples(int[] apples, int[] days) {
        Queue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int n = apples.length;

        int cnt = 0;
        int day = 0;
        while(day < n || !minHeap.isEmpty()) {
            while(!minHeap.isEmpty() && minHeap.peek()[1] <= day) {
                minHeap.poll();
            }

            if(day < n && apples[day] != 0) {
                minHeap.offer(new int[] {apples[day], day + days[day]});
            }

            if(!minHeap.isEmpty()) {
                if(--minHeap.peek()[0] == 0) {
                    minHeap.poll();
                }
                cnt++;
            }

            day++;
        }

        return cnt;
    }
}
