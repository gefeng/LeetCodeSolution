package solution.weekly290;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Number of Flowers in Full Bloom",
        difficulty = QDifficulty.HARD,
        tag = QTag.SORT,
        url = "https://leetcode.com/contest/weekly-contest-290/problems/number-of-flowers-in-full-bloom/"
)
public class Q2251 {
    /**
     * Time:  O(M * logM + N * logN + M * logN)
     * Space: O(M + N)
     * */
    public int[] fullBloomFlowers(int[][] flowers, int[] persons) {
        int n = flowers.length;
        int m = persons.length;
        int[] ans = new int[m];

        int[][] s = new int[m][2];

        for(int i = 0; i < m; i++) {
            s[i] = new int[] {persons[i], i};
        }

        Arrays.sort(s, Comparator.comparingInt(a -> a[0]));
        Arrays.sort(flowers, Comparator.comparingInt(a -> a[0]));

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));
        int i = 0;
        for(int[] p : s) {
            while(i < n && flowers[i][0] <= p[0]) {
                pq.offer(flowers[i++]);
            }

            while(!pq.isEmpty() && pq.peek()[1] < p[0]) {
                pq.poll();
            }

            ans[p[1]] = pq.size();
        }

        return ans;
    }

    /**
     * Time:  O((2 * M + N) * log(2 * M + N))
     * Space: O(2 * M + N)
     * */
    private int[] eventsSol(int[][] flowers, int[] persons) {
        int m = flowers.length;
        int n = persons.length;
        int[] ans = new int[n];
        int[][] events = new int[2 * m + n][3];

        for(int i = 0; i < m; i++) {
            events[2 * i] = new int[] {flowers[i][0], 0, -1};
            events[2 * i + 1] = new int[] {flowers[i][1], 2, -1};
        }

        for(int i = 0; i < n; i++) {
            events[2 * m + i] = new int[] {persons[i], 1, i};
        }

        Arrays.sort(events, (a, b) -> {
            if(a[0] == b[0]) {
                return Integer.compare(a[1], b[1]);
            }

            return Integer.compare(a[0], b[0]);
        });

        int cnt = 0;
        for(int[] e : events) {
            if(e[1] == 0) {
                cnt++;
            } else if(e[1] == 2) {
                cnt--;
            } else {
                ans[e[2]] = cnt;
            }
        }

        return ans;
    }
}
