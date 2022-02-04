package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Smallest Range Covering Elements from K Lists",
        difficulty = QDifficulty.HARD,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/"
)
public class Q632 {
    /**
     * Time:  O(K * N * logK)
     * Space: O(K)
     * */
    public int[] smallestRange(List<List<Integer>> nums) {
        int k = nums.size();
        int max = Integer.MIN_VALUE;
        int[] ans = new int[] {-(int)1e5, (int)1e5};
        Queue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> nums.get(a[0]).get(a[1])));

        for(int i = 0; i < k; i++) {
            pq.offer(new int[] {i, 0});
            max = Math.max(max, nums.get(i).get(0));
        }

        while(true) {
            int[] top = pq.poll();
            int min = nums.get(top[0]).get(top[1]);

            if((max - min < ans[1] - ans[0]) || (max - min == ans[1] - ans[0] && min < ans[0])) {
                ans[0] = min;
                ans[1] = max;
            }

            if(top[1] + 1 == nums.get(top[0]).size()) {
                break;
            }

            top[1]++;
            max = Math.max(max, nums.get(top[0]).get(top[1]));
            pq.offer(top);
        }

        return ans;
    }
}
