package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Reduce Array Size to The Half",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.ARRAY,
        url = "https://leetcode.com/problems/reduce-array-size-to-the-half/"
)
public class Q1338 {
    public int minSetSize(int[] arr) {
        int n = arr.length / 2;
        int[] cnt = new int[100001];

        for(int num : arr) {
            cnt[num]++;
        }

        Queue<Integer> maxHeap = new PriorityQueue<>((a, b) -> Integer.compare(b, a));
        int minSize = 0;
        for(int i = 0; i < cnt.length; i++) {
            if(cnt[i] == 0) {
                continue;
            }

            maxHeap.offer(cnt[i]);
        }

        while(n > 0) {
            n -= maxHeap.poll();
            minSize++;
        }

        return minSize;
    }
}
