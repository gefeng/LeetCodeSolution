package solution.biweekly82;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Minimum Sum of Squared Difference",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.GREEDY,
        url = "https://leetcode.com/contest/biweekly-contest-82/problems/minimum-sum-of-squared-difference/"
)
public class Q2333 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;

        Map<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < n; i++) {
            int d = Math.abs(nums1[i] - nums2[i]);
            map.put(d, map.getOrDefault(d, 0) + 1);
        }

        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparing(a -> a[0], Comparator.reverseOrder()));

        for(int k : map.keySet()) {
            pq.offer(new int[] {k, map.get(k)});
        }

        int tot = k1 + k2;
        while(true) {
            if(tot == 0) {
                break;
            }
            if(pq.peek()[0] == 0) {
                break;
            }

            int[] top = pq.poll();
            int d = top[0];
            int f = top[1];


            int t = pq.isEmpty() ? 0 : pq.peek()[0];

            int x = (d - t) * f;
            if(x <= tot) {
                if(!pq.isEmpty()) {
                    pq.peek()[1] += f;
                } else {
                    pq.offer(new int[] {t, f});
                }

                tot -= x;
            } else {
                int y = tot / f;
                d -= y;
                tot -= y * f;

                if(tot > 0) {
                    pq.offer(new int[] {d - 1, tot});
                    pq.offer(new int[] {d, f - tot});
                    tot = 0;
                } else {
                    pq.offer(new int[] {d, f});
                }
            }
        }

        long ans = 0;
        while(!pq.isEmpty()) {
            int[] top = pq.poll();
            ans += (long)top[0] * top[0] * top[1];
        }

        return ans;
    }
}
