package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "The Number of the Smallest Unoccupied Chair",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/the-number-of-the-smallest-unoccupied-chair/"
)
public class Q1942 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int smallestChair(int[][] times, int targetFriend) {
        int res = 0;
        int n = times.length;
        int targetTime = 0;
        Queue<int[]> used = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        Queue<Integer> free = new PriorityQueue<>();

        for(int i = 0; i < n; i++) {
            if(targetFriend == i) {
                targetTime = times[i][0];
            }
            free.offer(i);
        }

        Arrays.sort(times, Comparator.comparingInt(a -> a[0]));

        for(int[] t : times) {
            int arr = t[0];
            while(!used.isEmpty() && used.peek()[1] <= arr) {
                free.offer(used.poll()[0]);
            }

            int chair = free.poll();
            if(arr == targetTime) {
                res = chair;
                break;
            }
            used.offer(new int[] { chair, t[1] });
        }

        return res;
    }
}
