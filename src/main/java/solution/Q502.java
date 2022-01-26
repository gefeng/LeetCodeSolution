package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "IPO",
        difficulty = QDifficulty.HARD,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/ipo/"
)
public class Q502 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        int n = profits.length;
        int[][] projects = new int[n][2];

        for(int i = 0; i < n; i++) {
            projects[i][0] = profits[i];
            projects[i][1] = capital[i];
        }

        Arrays.sort(projects, Comparator.comparingInt(a -> a[1]));

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[0], Comparator.reverseOrder()));
        int p = 0;
        while(true) {
            if((p == n || projects[p][1] > w) && (pq.isEmpty() || k == 0)) break;

            while(p < n && projects[p][1] <= w) {
                pq.offer(projects[p++]);
            }

            if(!pq.isEmpty() && k > 0) {
                w += pq.poll()[0];
                k--;
            }
        }

        return w;
    }
}
