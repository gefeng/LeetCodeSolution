package solution.weekly288;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.PriorityQueue;
import java.util.Queue;

@Problem(
        title = "Maximum Product After K Increments",
        difficulty = QDifficulty.MEDIUM,
        tag = QTag.HEAP,
        url = "https://leetcode.com/contest/weekly-contest-288/problems/maximum-product-after-k-increments/"
)
public class Q2233 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public int maximumProduct(int[] nums, int k) {
        long mod = (long)1e9 + 7;
        int n = nums.length;

        Queue<Integer> pq = new PriorityQueue<>();

        for(int x : nums) {
            pq.offer(x);
        }

        while(k > 0) {
            int min = pq.poll();
            pq.offer(min + 1);
            k--;
        }

        long ans = 1;
        while(!pq.isEmpty()) {
            ans = ans * pq.poll() % mod;
        }

        return (int)ans;
    }
}
