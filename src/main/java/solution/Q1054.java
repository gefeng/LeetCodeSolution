package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Distance Barcodes",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/problems/distant-barcodes/"
)
public class Q1054 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int[] rearrangeBarcodes(int[] barcodes) {
        int[] cnt = new int[10001];
        for(int b : barcodes) {
            cnt[b]++;
        }

        Queue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if(a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(b[1], a[1]);
        });

        for(int i = 0; i < 10001; i++) {
            if(cnt[i] > 0) {
                pq.offer(new int[] {i, cnt[i]});
            }
        }

        int[] ans = new int[barcodes.length];
        int i = 0;
        while(!pq.isEmpty()) {
            int[] fst = pq.poll();
            ans[i++] = fst[0];
            fst[1]--;

            if(!pq.isEmpty()) {
                int[] sec = pq.poll();
                ans[i++] = sec[0];
                sec[1]--;
                if(sec[1] > 0) {
                    pq.offer(sec);
                }
            }

            if(fst[1] > 0) {
                pq.offer(fst);
            }
        }

        return ans;
    }
}
