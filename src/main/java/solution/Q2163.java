package solution;

import annotations.Problem;
import enums.QDifficulty;
import enums.QTag;

import java.util.*;

@Problem(
        title = "Minimum Difference in Sums After Removal of Elements",
        difficulty = QDifficulty.HARD,
        tag = QTag.HEAP,
        url = "https://leetcode.com/problems/minimum-difference-in-sums-after-removal-of-elements/"
)
public class Q2163 {
    /**
     * Time:  O(N * logN)
     * Space: O(N)
     * */
    public long minimumDifference(int[] nums) {
        int n = nums.length / 3;
        long ans = Long.MAX_VALUE;
        long sum1 = 0L;
        long sum2 = 0L;
        Queue<Integer> pqL = new PriorityQueue<>(Comparator.reverseOrder());
        Queue<Integer> pqR = new PriorityQueue<>();

        long[] prefix = new long[n + 1];
        for(int i = 0, j = 0; i < 2 * n; i++) {
            sum1 += nums[i];
            pqL.offer(nums[i]);

            if(pqL.size() > n) {
                sum1 -= pqL.poll();
            }

            if(pqL.size() == n) {
                prefix[j++] = sum1;
            }
        }

        for(int i = 3 * n - 1, j = n; i >= n; i--) {
            sum2 += nums[i];
            pqR.offer(nums[i]);

            if(pqR.size() > n) {
                sum2 -= pqR.poll();
            }

            if(pqR.size() == n) {
                ans = Math.min(ans, prefix[j--] - sum2);
            }
        }

        return ans;
    }
}
